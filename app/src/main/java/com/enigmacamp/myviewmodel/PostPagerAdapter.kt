package com.enigmacamp.myviewmodel

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.myviewmodel.repository.network.models.response.Post

class PostPagerAdapter :
    PagingDataAdapter<Post, PostPagerAdapter.PostListViewHolder>(PostComparator) {

    inner class PostListViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.tv_title)!!
    }

    object PostComparator : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val post = getItem(position)!!
        holder.txtTitle.text = post.id.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_viewholder, parent, false)
        return PostListViewHolder(view)
    }
}