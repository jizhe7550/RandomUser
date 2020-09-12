package com.jizhe7550.randomuser.api

import com.jizhe7550.randomuser.BuildConfig
import com.jizhe7550.randomuser.model.User

class RandomUserService(private val service: ApiService) {

    suspend fun searchUsers(
        page: Int,
        itemsPerPage: Int
    ): List<User> {
        val response = service.getUsers(BuildConfig.API_SEED, page, itemsPerPage)
        return response.users ?: listOf()
    }
}