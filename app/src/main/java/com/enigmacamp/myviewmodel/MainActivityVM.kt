package com.enigmacamp.myviewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class MainActivityVM(private val initialStarRating: Int) : ViewModel() {
    var totalBlogs: Int = 0

    fun printStarRating() {
        Log.d("Main-Activity-VN", initialStarRating.toString())
    }
}