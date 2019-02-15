package com.bensadiku.retrofitrxjavakotlin.ui.adapters

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bensadiku.retrofitrxjavakotlin.R
import com.bensadiku.retrofitrxjavakotlin.model.Posts


class  PostsRecyclerAdapter(val items : ArrayList<Posts>) : RecyclerView.Adapter<PostsRecyclerAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PostViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)
        return PostViewHolder(v)
    }


    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(parent: PostViewHolder, position: Int) {
        parent.bindViews(position)
    }


    inner class PostViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var txt_title: TextView = itemView.findViewById(R.id.txt_title)
        private var txt_content: TextView = itemView.findViewById(R.id.txt_content)
        private var txt_author: TextView = itemView.findViewById(R.id.txt_author)

        fun bindViews(position: Int){
            txt_title.text = items[position].title
            txt_content.text = items[position].body
            txt_author.text = items[position].userId.toString()
        }

    }

}