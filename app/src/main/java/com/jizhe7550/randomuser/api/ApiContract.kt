package com.jizhe7550.randomuser.api

import com.jizhe7550.randomuser.model.RandomUserResponse

interface ApiContract {

    suspend fun getUsers(): RandomUserResponse

}