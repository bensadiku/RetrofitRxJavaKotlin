package com.bensadiku.retrofitrxjavakotlin.data.network

import com.bensadiku.retrofitrxjavakotlin.model.Posts
import io.reactivex.Observable
import retrofit2.http.GET

interface TypeApi {

    @GET ("posts")
    fun getPosts():  Observable<ArrayList<Posts>>
}