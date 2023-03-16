package com.enigmacamp.myviewmodel.repository.network.models.response

import com.squareup.moshi.Json

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    @Json(name = "userId")
    val myUserId: Int
)
