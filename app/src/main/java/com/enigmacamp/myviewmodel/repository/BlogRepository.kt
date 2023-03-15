package com.enigmacamp.myviewmodel.repository

import com.enigmacamp.myviewmodel.repository.data.entities.Blog

interface BlogRepository {
    suspend fun getBlogs(): List<Blog>
    suspend fun registerBlogs(vararg blogs: Blog)
}