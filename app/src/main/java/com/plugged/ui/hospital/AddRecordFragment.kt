package com.plugged.ui.hospital

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.plugged.Auth.LoginFragment
import com.plugged.R
import com.plugged.models.AddRecord
import com.plugged.utils.Constants.Companion.SHARED_PREF
import com.plugged.utils.Constants.Companion.TAG
import com.plugged.utils.Resource
import com.plugged.viewmodel.PluggedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_record.*

@AndroidEntryPoint
class AddRecordFragment : Fragment() {
    private val viewModel: PluggedViewModel by viewModels()
    var token = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_record, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       viewModel.getToken().observe(viewLifecycleOwner, Observer {data->

         if (data !=null)
         {
             token = data.token
         }
           Log.e(TAG,token)



       })


        btn_add.setOnClickListener {

            val symptoms = edit_symptoms.text.toString()
            val diagnosis = edit_diagnosis.text.toString()
            val prescription = edit_prescription.text.toString()
            val allergies = edit_allergies.text.toString()
            val notes = edit_notes.text.toString()
            val patientEmail = edit_patientEmail.text.toString()

            val record =
                AddRecord(allergies, diagnosis, notes, patientEmail, prescription, symptoms)


            if (token !== "") {
                viewModel.addPatientRecord(record)
                Log.e(TAG,"request sent")


            }



        }

        viewModel.addRecord.observe(viewLifecycleOwner, Observer { response ->

            when (response) {

                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    Log.e(TAG,"LOADING")

                }

                is Resource.Success -> {
                    response.data?.let { data ->
                        progressBar.visibility = View.INVISIBLE
                        Log.e(TAG,"SUCCESS")

                        Log.e(TAG,data.toString())
                    }

                }

                is Resource.Error -> {
                    progressBar.visibility = View.INVISIBLE
                    response.message?.let {
                        Log.e(TAG,it)
                        Log.e(TAG,"ERROR")

                        Toast.makeText(activity, "$it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        })



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
         token = sharedPref.getString(SHARED_PREF, "No value").toString()
    }

}