package com.enigmacamp.myviewmodel.util

import android.app.Activity
import com.enigmacamp.myviewmodel.BaseApplication
import com.enigmacamp.myviewmodel.repository.data.AppDatabase

val Activity.db: AppDatabase
    get() = (application as BaseApplication).appDatabase