package com.jizhe7550.randomuser.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jizhe7550.randomuser.model.User

/**
 * UserSearchResult from a search, which contains LiveData<List<User>> holding query data,
 * and a LiveData<String> of network error state.
 */
data class UserSearchResult(
    val data: LiveData<PagedList<User>>,
    val networkErrors: LiveData<String>
)