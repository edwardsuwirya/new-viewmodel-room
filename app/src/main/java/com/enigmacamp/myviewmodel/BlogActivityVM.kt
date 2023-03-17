package com.enigmacamp.myviewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.enigmacamp.myviewmodel.repository.BlogRepository
import com.enigmacamp.myviewmodel.repository.PostRepository
import com.enigmacamp.myviewmodel.repository.data.entities.Blog
import com.enigmacamp.myviewmodel.repository.network.models.response.Post
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
                _postLiveData.postValue(ViewState.success(postRepository.getPost()))
            } catch (e: Exception) {
                _postLiveData.postValue(ViewState.error(e.message))
            }
        }
    }

    fun onGetAllPost() =
        postRepository.getAllPost().cachedIn(viewModelScope)

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