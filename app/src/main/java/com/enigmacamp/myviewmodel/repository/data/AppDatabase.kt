package com.enigmacamp.myviewmodel.repository.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enigmacamp.myviewmodel.repository.data.dao.BlogDao
import com.enigmacamp.myviewmodel.repository.data.entities.Blog

@Database(entities = [Blog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "simple_room"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}