package dev.queen.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

//    Request for getting the posts
    @GET  ("/posts")
    fun getPosts(): Call<List<Post>>

//    Request for getting a single post by its Id
    @GET  ("/posts/{id}")
    fun getPostById(@Path("id") postId: Int): Call<Post>

//    Request for getting a comments of a single post
    @GET  ("/posts/{id}/comments")
    fun getComments(@Path("id/comment") commentId: Int):Call <Comments>


}