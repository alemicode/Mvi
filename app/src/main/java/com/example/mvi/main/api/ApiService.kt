package com.example.mvi.main.api

import androidx.lifecycle.LiveData
import com.example.mvi.main.model.BlogPost
import com.example.mvi.main.model.User
import com.example.mvi.main.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/user/{userId}")
    fun getUser(

        @Path("userId") userId: String

    ): LiveData<GenericApiResponse<User>>

    @GET("placeholder/blogs")
    fun getBlogs(): LiveData<GenericApiResponse<List<BlogPost>>>


}

