package com.enigmacamp.myviewmodel.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.enigmacamp.myviewmodel.repository.network.models.response.Post
import com.enigmacamp.simpleviewmodel.ViewState

interface PostRepository {
    suspend fun getPost(): Post
    fun getAllPost(): LiveData<PagingData<Post>>
}