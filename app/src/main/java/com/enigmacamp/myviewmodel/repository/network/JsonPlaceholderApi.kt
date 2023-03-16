package com.enigmacamp.myviewmodel.repository.network

import com.enigmacamp.myviewmodel.repository.network.models.response.Post
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("posts/1")
    suspend fun getPost(): Response<Post>
}