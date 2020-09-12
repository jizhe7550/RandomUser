package com.jizhe7550.randomuser.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.jizhe7550.randomuser.RadomUserIdlingResource
import com.jizhe7550.randomuser.api.RandomUserService
import com.jizhe7550.randomuser.db.RandomUserLocalCache
import com.jizhe7550.randomuser.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * This boundary callback gets notified when user reaches to the edges of the list for example when
 * the database cannot provide any more data.
 **/
class UsersBoundaryCallback(
    private val query: String,
    private val service: RandomUserService,
    private val cache: RandomUserLocalCache,
    private val scope: CoroutineScope,
    private val idlingResource: RadomUserIdlingResource
) : PagedList.BoundaryCallback<User>() {

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()

    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        Timber.d("RepoBoundaryCallback: onZeroItemsLoaded")
        scope.launch {
            requestAndSaveData(query)
        }
    }

    /**
     * When all items in the database were loaded, we need to query the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: User) {
        Timber.d("RepoBoundaryCallback: onItemAtEndLoaded")
        scope.launch {
            requestAndSaveData(query)
        }
    }

    private suspend fun requestAndSaveData(query: String) {
        idlingResource.increment()
        try {
            if (isRequestInProgress) return
            isRequestInProgress = true
            val users = service.searchUsers(lastRequestedPage, NETWORK_PAGE_SIZE)
            cache.insert(users) {
                lastRequestedPage++
                isRequestInProgress = false
            }
        } catch (e: Exception) {
            Timber.e("UsersBoundaryCallback.requestAndSaveData: ${e.message}")
            _networkErrors.postValue(e.message)
        } finally {
            idlingResource.decrement()
        }

    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}