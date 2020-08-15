package com.example.mvi.main.ui.main.state

import com.example.mvi.main.model.BlogPost
import com.example.mvi.main.model.User

data class MainViewState(

    var blogBlogPost : List<BlogPost>? = null,
    var user : User? = null

){

    override fun toString(): String {
        return "MainViewState has been called"
    }

}
