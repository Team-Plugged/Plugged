package com.plugged.ui.hospital

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.plugged.R
import com.plugged.models.SearchBody
import com.plugged.utils.Constants.Companion.SEARCH_DELAY
import com.plugged.utils.Constants.Companion.TAG
import com.plugged.viewmodel.PluggedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import androidx.lifecycle.Observer
import com.plugged.utils.Resource

import java.util.*


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: PluggedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var job: Job? = null

        viewModel.searchRecord.observe(viewLifecycleOwner, Observer {response->

            when (response) {

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    response.data?.let { data ->
                        Log.d(TAG,data.toString())
                    }

                }

                is Resource.Error -> {

                    response.message?.let {
                        Toast.makeText(activity, "$it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }



        })



        edit_search.addTextChangedListener(object : TextWatcher {
            var timer = Timer()
            override fun afterTextChanged(s: Editable?) {

                if (s?.length!! > 7)
                {
                    val query  =SearchBody(s.toString())
                    Log.e("TAG","timer start")
                    timer.schedule(object : TimerTask() {
                        override fun run() {
                                viewModel.search(query)
                        }
                    }, 3000)
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.e("TAG","timer cancel ")
                timer.cancel() //Terminates this timer,discarding any currently scheduled tasks.
                timer.purge() //Removes all cancelled tasks from this timer's task queue.
            }
        })



    }

}