package com.enigmacamp.myviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.myviewmodel.repository.BlogRepository
import com.enigmacamp.myviewmodel.repository.PostRepository
import com.enigmacamp.myviewmodel.repository.data.entities.Blog
import com.enigmacamp.simpleviewmodel.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BlogActivityVM(
    private val blogRepository: BlogRepository,
    private val postRepository: PostRepository
) : ViewModel() {
    private var _blogListLiveData = MutableLiveData<ViewState>()
    val blogListLiveData: LiveData<ViewState>
        get() = _blogListLiveData

    private var _postLiveData = MutableLiveData<ViewState>()
    val postLiveData: LiveData<ViewState>
        get() = _postLiveData

    fun onGetPost() {
        viewModelScope.launch(Dispatchers.IO) {
            _postLiveData.postValue(ViewState.loading())
            try {
                val result = postRepository.getPost()
                _postLiveData.postValue(ViewState.success(result))
            } catch (e: Exception) {
                _postLiveData.postValue(ViewState.error(e.message))
            }
        }
    }

    fun onGetBlog() = dbTrans {
        blogRepository.getBlogs()
    }

    fun onInsertBlog() = dbTrans {
        val newBlog = Blog(1, "Kotlin", "Lorem ipsum", "Enigma")
        blogRepository.registerBlogs(newBlog)
        newBlog
    }

    private fun dbTrans(fn: suspend () -> Any) {
        viewModelScope.launch(Dispatchers.IO) {
            _blogListLiveData.postValue(ViewState.loading())
            val res = fn()
            _blogListLiveData.postValue(ViewState.success(res))
        }
    }
}