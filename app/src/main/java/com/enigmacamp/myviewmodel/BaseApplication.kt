package com.enigmacamp.myviewmodel

import android.app.Application
import com.enigmacamp.myviewmodel.repository.data.AppDatabase

class BaseApplication : Application() {
    lateinit var appDatabase: AppDatabase
    override fun onCreate() {
        super.onCreate()
        appDatabase = AppDatabase.getDatabase(this)
    }
}