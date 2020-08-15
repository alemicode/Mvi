package com.example.mvi.main.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvi.main.model.BlogPost
import com.example.mvi.main.model.User
import com.example.mvi.main.ui.main.state.MainStateEvent
import com.example.mvi.main.ui.main.state.MainViewState
import com.example.mvi.main.util.AbsentLiveData

class MainViewModel : ViewModel() {


    val _viewState: MutableLiveData<MainViewState> = MutableLiveData()
    val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()


    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent) {

            it?.let {
                handleStateEvent(it)
            }
        }

    //handle each condition of state event
    fun handleStateEvent(it: MainStateEvent): LiveData<MainViewState> {
        when (it) {
            is MainStateEvent.GetBlogPost -> {


                return AbsentLiveData.create()

            }
            is MainStateEvent.GetUserEvent -> {
                return AbsentLiveData.create()
            }
            is MainStateEvent.None -> {
                return AbsentLiveData.create()
            }
        }
    }


    //setting blog post and update livedata
    fun setBlogPost(list: List<BlogPost>) {

        val update = getCurrentViewStateOrNew()
        update.blogBlogPost = list
        _viewState.postValue(update)
    }

    fun setUser(user: User) {
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.postValue(update)
    }

    //make or get current refrence of MainViewState
    fun getCurrentViewStateOrNew(): MainViewState {
        val value = viewState.value?.let {
            it
        } ?: MainViewState()

        return value
    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event

    }


}