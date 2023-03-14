package com.enigmacamp.myviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.simpleviewmodel.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityVM(private val initialStarRating: Int) : ViewModel() {
    var totalBlog: Int = 0
    var totalBlogsLiveData = MutableLiveData<ViewState>()

    fun printStarRating() {
        viewModelScope.launch(Dispatchers.IO) {
            totalBlogsLiveData.postValue(ViewState.loading())
            delay(2000)
            Log.d("Main-Activity-VM", totalBlogsLiveData.value?.data.toString())
            totalBlog = totalBlog.plus(1).times(initialStarRating)
            if (totalBlog == 156) {
                totalBlogsLiveData.postValue(ViewState.error("Simulasi error"))
            } else {
                totalBlogsLiveData.postValue(ViewState.success(totalBlog))
            }

        }
    }
}