package com.example.mvi.main.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.mvi.R
import com.example.mvi.main.ui.DataStateListener
import com.example.mvi.main.util.Constant
import com.example.mvi.main.util.DataState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DataStateListener {

    val x: MutableLiveData<Constant> = MutableLiveData()

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        showMainFragment()


    }


    fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                MainFragment(), "MainFragment"
            )
            .commit()
    }

    override fun onDataStateChange(dataSate: DataState<*>) {

        handleDataStateChange(dataSate)
    }

    private fun handleDataStateChange(dataSate: DataState<*>) {

        dataSate?.let {


            //handle loading

            showProgressBar(it.loading)

            //handle message
            it.message?.let {

                it.getContentIfNotHandled()?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

                }

            }


        }

    }

    fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {

            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE

        }
    }


}
