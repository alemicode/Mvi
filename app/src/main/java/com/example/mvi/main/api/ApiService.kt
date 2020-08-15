package com.example.mvi.main.api

import com.example.mvi.main.model.BlogPost
import com.example.mvi.main.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/user/{userId}")
    fun getUser(

        @Path("userId") userId: String

    ): User

    @GET("placeholder/blogs")
    fun getBlogs(): List<BlogPost>
}