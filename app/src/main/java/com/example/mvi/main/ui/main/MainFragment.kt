package com.example.mvi.main.ui.main


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvi.R
import com.example.mvi.main.ui.main.state.MainStateEvent
import java.lang.Exception


/**
 * Crated by Mohamad Alemi
 */
class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragent_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        subscribeObservers()


    }

    private fun subscribeObservers() {

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("not current activity")

        viewModel.dataState.observe(viewLifecycleOwner, Observer {


            println("JESS : data state observe")
            it.blogBlogPost?.let {

                viewModel.setBlogPost(it)
            }

            it.user?.let {
                viewModel.setUser(it)

            }


        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer {

            println("JESS : viewState")
        })


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.action_getUser -> {

                triggleGetUserEvent()
            }

            R.id.actionGetBlog -> {
                triggleGetBlogPostEvent()
            }
        }
    }

    private fun triggleGetBlogPostEvent() {
        viewModel.setStateEvent(MainStateEvent.GetBlogPost())
    }

    private fun triggleGetUserEvent() {

        viewModel.setStateEvent(MainStateEvent.GetUserEvent("1"))
    }

}
