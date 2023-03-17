package com.enigmacamp.myviewmodel.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.enigmacamp.myviewmodel.repository.network.JsonPlaceholderApi
import com.enigmacamp.myviewmodel.repository.network.models.response.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostPagingSource(private val jsonPlaceholderApi: JsonPlaceholderApi) :
    PagingSource<Int, Post>() {
    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            withContext(Dispatchers.IO) {
                val position = params.key ?: 1
                val response =
                    jsonPlaceholderApi.getAllPost((position - 1) * 10, 10)
                if (response.isSuccessful) {
                    val post = response.body()
                    post?.let {
                        if (post.isNotEmpty()) {
                            LoadResult.Page(
                                data = response.body()!!,
                                prevKey = if (position == 1) null else position - 1,
                                nextKey = position + 1
                            )
                        } else {
                            throw Exception("No data")
                        }

                    } ?: throw Exception("No data")
                } else {
                    throw Exception("Failed to get response")
                }
            }


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}