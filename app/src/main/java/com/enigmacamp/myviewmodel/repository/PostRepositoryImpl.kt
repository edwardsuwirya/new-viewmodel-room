package com.enigmacamp.myviewmodel.repository

import com.enigmacamp.myviewmodel.repository.network.RetrofitInstance
import com.enigmacamp.myviewmodel.repository.network.models.response.Post

class PostRepositoryImpl : PostRepository {
    override suspend fun getPost(): Post {
        val response = RetrofitInstance.jsonPlaceholderApi.getPost()
        if (response.isSuccessful) {
            val post = response.body()
            post?.let {
                return it
            } ?: throw Exception("No data")
        } else {
            throw Exception("Failed to get response")
        }
    }
}