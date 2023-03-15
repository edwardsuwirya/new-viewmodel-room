package com.enigmacamp.myviewmodel.repository.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.enigmacamp.myviewmodel.repository.data.entities.Blog

@Dao
interface BlogDao {
    @Query("SELECT * FROM m_blog")
    suspend fun getAll(): List<Blog>

    @Query("SELECT * FROM m_blog WHERE author IN (:userIds)")
    suspend fun loadAllByAuthor(userIds: IntArray): List<Blog>

    @Insert
    suspend fun insertAll(vararg blogs: Blog)

    @Delete
    suspend fun delete(blog: Blog)
}