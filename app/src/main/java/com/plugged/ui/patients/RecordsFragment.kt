package com.plugged.ui.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plugged.Adapters.RecordsRecyclerView
import com.plugged.R
import com.plugged.models.HealthRecordsResponseItem
import com.plugged.utils.Constants
import com.plugged.utils.Resource
import com.plugged.viewmodel.PluggedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_records.*

@AndroidEntryPoint
class RecordsFragment : Fragment() {
    private val viewModel: PluggedViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var records: List<HealthRecordsResponseItem>
    var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.recycler_view)
        records = listOf()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
        viewModel.getPatient().observe(viewLifecycleOwner, Observer { patient ->

            if (patient  !=null)
            {
                email = patient.email
            }

        })

        viewModel.get_record()


        viewModel.healthRecord.observe(viewLifecycleOwner, Observer { response ->


            when (response) {

                is Resource.Loading -> {

                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.INVISIBLE
                    response?.data.let {
                        if (it != null) {
                            records = it.filter {
                                it.patientEmail == email
                            }
                        }

                        val adapter = RecordsRecyclerView(records)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()

                    }


                }

                is Resource.Error -> {
                    progressBar.visibility = View.INVISIBLE


                    response.message?.let {
                        Toast.makeText(activity, "Error Occured: $it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }


            }

        })
    }

}