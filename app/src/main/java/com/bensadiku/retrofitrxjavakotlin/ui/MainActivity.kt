package com.bensadiku.retrofitrxjavakotlin.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bensadiku.retrofitrxjavakotlin.R
import com.bensadiku.retrofitrxjavakotlin.ui.adapters.PostsRecyclerAdapter

import com.bensadiku.retrofitrxjavakotlin.data.network.RetrofitClient
import com.bensadiku.retrofitrxjavakotlin.data.network.TypeApi
import com.bensadiku.retrofitrxjavakotlin.model.Posts
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var typeApi: TypeApi
    private lateinit var recycler_posts: RecyclerView
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initializing API

        val retrofit = RetrofitClient.retrofitInstace
        typeApi = retrofit.create(TypeApi::class.java)

        recycler_posts = findViewById(R.id.recycler_posts)
        recycler_posts.setHasFixedSize(true)
        recycler_posts.layoutManager = LinearLayoutManager(this)

        fetchData()
    }

    private fun fetchData() {

        compositeDisposable.add(typeApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { posts -> displayData(posts) }
        )

    }

    private fun displayData(posts: ArrayList<Posts>) {
        val adapter = PostsRecyclerAdapter(posts)
        recycler_posts.adapter = adapter
    }


    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }
}
