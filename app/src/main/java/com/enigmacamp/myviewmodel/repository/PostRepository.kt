package com.enigmacamp.myviewmodel.repository

import com.enigmacamp.myviewmodel.repository.network.models.response.Post

interface PostRepository {
    suspend fun getPost(): Post
}