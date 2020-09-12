package com.jizhe7550.randomuser.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomUserResponse(
    @Json(name = "info")
    val info: Info?,

    @Json(name = "results")
    val users: List<User>?
)