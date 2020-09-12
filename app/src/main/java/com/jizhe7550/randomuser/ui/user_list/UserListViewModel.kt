package com.jizhe7550.randomuser.ui.user_list

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.jizhe7550.randomuser.base.BaseViewModel
import com.jizhe7550.randomuser.common.mapWithDefault
import com.jizhe7550.randomuser.data.RandomUserRepository
import com.jizhe7550.randomuser.model.User
import com.jizhe7550.randomuser.ui.user_list.UserListEvent

class UserListViewModel(
    private val repository: RandomUserRepository?
) : BaseViewModel<UserListEvent>() {

    private val _randomUsers = repository?.getUsers(viewModelScope)
    val randomUsers: LiveData<PagedList<User>>? = _randomUsers?.data
    val networkError: LiveData<String>? = _randomUsers?.networkErrors

    val emptyResultVisibility =
        _randomUsers?.data?.mapWithDefault(View.GONE) { if (it.isEmpty()) View.VISIBLE else View.GONE }

    fun onUserClicked(user: User, position: Int) {
        postEvent(UserListEvent.GoToUserDetail(user, position))
    }

    fun onNetworkError(msg: String) {
        postEvent(UserListEvent.DisplayError(msg))
    }
}