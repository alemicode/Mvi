package com.example.mvi.main.repository

import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mvi.main.api.MyRetrofitBuilder
import com.example.mvi.main.model.BlogPost
import com.example.mvi.main.model.User
import com.example.mvi.main.ui.main.state.MainViewState
import com.example.mvi.main.util.*

object Repository {

    fun getBlogPost(): LiveData<DataState<MainViewState>> {

        return object : NetworkBoundResourse<List<BlogPost>, MainViewState>() {


            override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {

                return MyRetrofitBuilder.apiService.getBlogs()
            }

            override fun handleApiSuccessResponse(it: ApiSuccessResponse<List<BlogPost>>) {

                result.value = DataState.data(
                    data = MainViewState(
                        it.body
                    )
                )
            }

        }.asLiveData()
    }



    fun getUser(): LiveData<DataState<MainViewState>> {

        return object : NetworkBoundResourse<User, MainViewState>() {
            override fun handleApiSuccessResponse(it: ApiSuccessResponse<User>) {

                result.value = DataState.data(
                    data = MainViewState(user = User())
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {

                return MyRetrofitBuilder.apiService.getUser("1")
            }

        }.asLiveData()
    }

}