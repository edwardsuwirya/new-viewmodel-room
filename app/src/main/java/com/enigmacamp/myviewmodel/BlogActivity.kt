package com.enigmacamp.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.myviewmodel.repository.BlogRepository
import com.enigmacamp.myviewmodel.repository.BlogRepositoryImpl
import com.enigmacamp.myviewmodel.repository.PostRepository
import com.enigmacamp.myviewmodel.repository.PostRepositoryImpl
import com.enigmacamp.myviewmodel.repository.data.AppDatabase
import com.enigmacamp.myviewmodel.repository.data.dao.BlogDao
import com.enigmacamp.myviewmodel.util.db
import com.enigmacamp.simpleviewmodel.ViewStatus

class BlogActivity : AppCompatActivity() {
    private lateinit var viewModel: BlogActivityVM
    private lateinit var blogRepo: BlogRepository
    private lateinit var postRepo: PostRepository
    private lateinit var blogDao: BlogDao

    private lateinit var tvResult: TextView
    private lateinit var loadingFragment: LoadingFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        blogDao = db.blogDao()
        blogRepo = BlogRepositoryImpl(blogDao)
        postRepo = PostRepositoryImpl()
        initUI()
        initViewModel()
        subscribe()
    }

    private fun initUI() {
        val btnBlog = findViewById<Button>(R.id.btn_blog)
        val btnPost = findViewById<Button>(R.id.btn_post)
        tvResult = findViewById(R.id.tv_result)

        btnBlog.setOnClickListener {
//            viewModel.onInsertBlog()
            viewModel.onGetBlog()
        }
        btnPost.setOnClickListener {
            viewModel.onGetPost()
        }
        loadingFragment = LoadingFragment.newInstance()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BlogActivityVM(blogRepo, postRepo) as T
            }
        })[BlogActivityVM::class.java]
    }

    private fun showLoadingFragment() {
        loadingFragment.show(supportFragmentManager, "dialog")
    }

    private fun dismissLoadingFragment() {
        loadingFragment.dismiss()
    }

    private fun subscribe() {
        viewModel.blogListLiveData.observe(this) {
            when (it.status) {
                ViewStatus.SUCCESS -> {
                    tvResult.text = it.data.toString()
                    dismissLoadingFragment()
                }
                ViewStatus.LOADING -> showLoadingFragment()
                ViewStatus.ERROR -> {
                    tvResult.text = it.message.toString()
                    dismissLoadingFragment()
                }
            }
        }
        viewModel.postLiveData.observe(this) {
            when (it.status) {
                ViewStatus.SUCCESS -> {
                    tvResult.text = it.data.toString()
                    dismissLoadingFragment()
                }
                ViewStatus.LOADING -> showLoadingFragment()
                ViewStatus.ERROR -> {
                    tvResult.text = it.message.toString()
                    dismissLoadingFragment()
                }
            }
        }
    }
}