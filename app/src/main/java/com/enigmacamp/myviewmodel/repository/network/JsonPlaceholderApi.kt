package com.enigmacamp.myviewmodel.repository.network

import com.enigmacamp.myviewmodel.repository.network.models.response.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonPlaceholderApi {
    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    @GET("posts")
    suspend fun getAllPost(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): Response<List<Post>>
}