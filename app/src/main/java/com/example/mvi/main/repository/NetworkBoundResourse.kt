package com.example.mvi.main.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.mvi.main.util.*
import com.example.mvi.main.util.Constant.Companion.TESTING_NETWORK_DELAY
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


abstract class NetworkBoundResourse<ResponseObject, ViewStateType> {

    /*

    Response Object Result getting from Retrofit Like List<User>

    ViewState is class for each viewState in this project just we have MainViewState

     */


    protected val result = MediatorLiveData<DataState<ViewStateType>>()

    init {

        //as soon as the class initilize we want to show the progress bar
        result.value = DataState.loading(true)

        GlobalScope.launch(IO) {
            delay(TESTING_NETWORK_DELAY)

            withContext(Main) {

                val apiResponse = createCall()
                result.addSource(apiResponse) {
                    result.removeSource(apiResponse)
                    handleNetworkCall(it)
                }

            }
        }
    }

    private fun handleNetworkCall(it: GenericApiResponse<ResponseObject>) {

        when (it) {
            is ApiSuccessResponse -> {

                handleApiSuccessResponse(it)

            }

            is ApiEmptyResponse -> {
                println("Debug : NetworkBoundResource :")
                onError("NetworkBoundResource")
            }
            is ApiErrorResponse -> {

                println("Debug : NetworkBoundResource :")
                onError("${it.errorMessage}")
            }
        }
    }

    fun onError(msg: String) {
        result.value = DataState.error(msg)
    }

    abstract fun handleApiSuccessResponse(it: ApiSuccessResponse<ResponseObject>)


    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

    fun asLiveData() = result as LiveData<DataState<ViewStateType>>
}

