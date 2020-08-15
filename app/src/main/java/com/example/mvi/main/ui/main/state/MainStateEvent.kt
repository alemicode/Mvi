package com.example.mvi.main.ui.main.state

import androidx.lifecycle.LiveData

sealed class MainStateEvent {

    class GetBlogPost : MainStateEvent()

    class GetUserEvent(
        val id: String
    ) : MainStateEvent()

    class None : MainStateEvent()

    override fun toString(): String {
        return "MainStateEvent has been called"
    }
}