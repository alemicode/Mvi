package com.example.mvi.main.ui.main


import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvi.R
import com.example.mvi.main.ui.DataStateListener
import com.example.mvi.main.ui.main.state.MainStateEvent
import java.lang.Exception


/**
 * Crated by Mohamad Alemi
 */
class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel


    lateinit var dataStateHandler: DataStateListener
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

        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->


            dataStateHandler.onDataStateChange(dataState)

            dataState.data.let { eventState ->

                eventState?.getContentIfNotHandled()?.let {
                    it.blogBlogPost?.let {

                        viewModel.setBlogPost(it)

                    }

                    it.user?.let {

                        viewModel.setUser(it)
                    }
                }

            }
        })



        viewModel.viewState.observe(viewLifecycleOwner, Observer {

            it.blogBlogPost?.let {
                println("JESS : viewState ${it}")

                Toast.makeText(context, "${it}", Toast.LENGTH_SHORT).show()

            } ?: println("jess erorr")


            it.user?.let {
                Toast.makeText(context, "${it}", Toast.LENGTH_SHORT).show()

            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_getUser -> {

                triggleGetUserEvent()
            }

            R.id.actionGetBlog -> {
                triggleGetBlogPostEvent()
            }
        }
        return true
    }

    private fun triggleGetBlogPostEvent() {
        viewModel.setStateEvent(MainStateEvent.GetBlogPost())
    }

    private fun triggleGetUserEvent() {

        viewModel.setStateEvent(MainStateEvent.GetUserEvent("1"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


        try {
            dataStateHandler = context as DataStateListener

        } catch (e: Exception) {
            println("Debug : ")
        }

    }

}
