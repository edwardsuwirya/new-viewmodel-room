package com.enigmacamp.myviewmodel.repository

import com.enigmacamp.myviewmodel.repository.data.dao.BlogDao
import com.enigmacamp.myviewmodel.repository.data.entities.Blog

class BlogRepositoryImpl(private val blogDao: BlogDao) : BlogRepository {
    override suspend fun getBlogs() = blogDao.getAll()
    override suspend fun registerBlogs(vararg blogs: Blog) = blogDao.insertAll(*blogs)
}