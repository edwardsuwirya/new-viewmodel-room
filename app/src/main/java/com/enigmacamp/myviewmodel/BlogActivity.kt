package com.enigmacamp.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.myviewmodel.repository.BlogRepository
import com.enigmacamp.myviewmodel.repository.BlogRepositoryImpl
import com.enigmacamp.myviewmodel.repository.data.AppDatabase
import com.enigmacamp.myviewmodel.repository.data.dao.BlogDao
import com.enigmacamp.myviewmodel.util.db
import com.enigmacamp.simpleviewmodel.ViewStatus

class BlogActivity : AppCompatActivity() {
    private lateinit var viewModel: BlogActivityVM
    private lateinit var blogRepo: BlogRepository
    private lateinit var blogDao: BlogDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        blogDao = db.blogDao()
        blogRepo = BlogRepositoryImpl(blogDao)
        initUI()
        initViewModel()
        subscribe()
    }

    private fun initUI() {
        val btnBlog = findViewById<Button>(R.id.btn_blog)

        btnBlog.setOnClickListener {
//            viewModel.onInsertBlog()
            viewModel.onGetBlog()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BlogActivityVM(blogRepo) as T
            }
        })[BlogActivityVM::class.java]
    }

    private fun subscribe() {
        viewModel.blogListLiveData.observe(this) {
            when (it.status) {
                ViewStatus.SUCCESS -> Log.d("Blog", it.data.toString())
                ViewStatus.LOADING -> Log.d("Blog", "Loading")
                ViewStatus.ERROR -> Log.d("Blog", it.message.toString())
            }
        }
    }
}