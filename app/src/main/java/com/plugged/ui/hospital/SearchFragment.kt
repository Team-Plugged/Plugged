package com.plugged.ui.hospital

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.plugged.R
import com.plugged.utils.Constants.Companion.SEARCH_DELAY
import com.plugged.utils.Constants.Companion.TAG
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

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

        edit_search.addTextChangedListener {text->

            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_DELAY)
                text.let {
                    if (text.toString().isNotEmpty())
                    {
                        progressBar.visibility = View.VISIBLE
                       Log.d(TAG,text.toString())
                    }
                }
            }

        }

    }

}