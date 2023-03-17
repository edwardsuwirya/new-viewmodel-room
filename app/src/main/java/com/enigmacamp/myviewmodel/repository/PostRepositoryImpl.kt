package com.enigmacamp.myviewmodel.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.enigmacamp.myviewmodel.repository.network.JsonPlaceholderApi
import com.enigmacamp.myviewmodel.repository.network.models.response.Post

class PostRepositoryImpl(private val jsonPlaceholderApi: JsonPlaceholderApi) : PostRepository {
    override suspend fun getPost(): Post {
        val response = jsonPlaceholderApi.getPost()
        if (response.isSuccessful) {
            val post = response.body()

            post?.let {
                return (it)
            } ?: throw Exception("No data")
        } else {
            throw Exception("Failed")
        }
    }

    override fun getAllPost(): LiveData<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                PostPagingSource(jsonPlaceholderApi)
            }, initialKey = 1
        ).liveData
    }
}