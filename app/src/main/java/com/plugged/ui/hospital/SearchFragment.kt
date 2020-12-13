package com.plugged.ui.hospital

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.plugged.R
import com.plugged.models.LoginResponse
import com.plugged.models.SearchBody
import com.plugged.models.SearchResponse
import com.plugged.utils.Constants.Companion.TAG
import com.plugged.utils.Resource
import com.plugged.viewmodel.PluggedViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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

        viewModel.searchRecord.observe(viewLifecycleOwner, Observer { response ->

            when (response) {

                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE

                }

                is Resource.Success -> {
                    progressBar.visibility = View.INVISIBLE

                    response.data?.let { data ->

                        setData(data)
                        Log.d(TAG, data.toString())
                    }

                }

                is Resource.Error -> {
                    progressBar.visibility = View.INVISIBLE

                    response.message?.let {
                        Log.d(TAG,it.toString())
                        Toast.makeText(activity, "$it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }


        })

        var job: Job? = null

        edit_search.addTextChangedListener {editable->

            job?.cancel()
            job = MainScope().launch {
                delay(3000)
                editable.let {
                    if (editable.toString().isNotEmpty())
                    {
                        val query = SearchBody(editable.toString())
                            viewModel.search(query)
                    }
                }
            }

        }

    }

    fun setData(patient: SearchResponse)
    {
        scrollView.visibility = View.VISIBLE
        gender.text = patient.gender
        weight.text =patient.weight.toString()
        height.text = patient.height.toString()
        f_name.text = patient.firstname
        l_name.text = patient.lastname
        email.text = patient.email
        text_dob.text = patient.age.toString()
//        text_phone.text = patien
//        address.text = patient.

        Picasso.get()
            .load(patient.image)
            .into(profile_image)


    }

}