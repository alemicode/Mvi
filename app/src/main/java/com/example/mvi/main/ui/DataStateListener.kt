package com.example.mvi.main.ui

import com.example.mvi.main.util.DataState

interface DataStateListener {

    fun onDataStateChange (dataSate: DataState<*>)
}