package com.plugged.ui.hospital

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.plugged.Auth.LoginFragment
import com.plugged.R
import com.plugged.models.AddRecord
import com.plugged.ui.home.PatientActivity
import com.plugged.utils.MyPreferences
import com.plugged.utils.Resource
import com.plugged.viewmodel.PluggedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_record.*
import kotlinx.android.synthetic.main.fragment_login.view.*

@AndroidEntryPoint
class AddRecordFragment : Fragment() {
    private val viewModel: PluggedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_record, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        btn_add.setOnClickListener {

            val symptoms = edit_symptoms.text.toString()
            val diagnosis = edit_diagnosis.text.toString()
            val prescription = edit_prescription.text.toString()
            val allergies = edit_allergies.text.toString()
            val notes = edit_notes.text.toString()
            val patientEmail = edit_patientEmail.text.toString()

            val record = AddRecord(allergies,diagnosis,notes,patientEmail,prescription,symptoms)

            val token = MyPreferences(activity).token


                if (token != null && token!== "") {
                    viewModel.addPatientRecord(token,record)

            }

            viewModel.addRecord.observe(viewLifecycleOwner, Observer {response->

                when (response) {

                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        response.data?.let { data ->
                            Toast.makeText(activity, data.toString(), Toast.LENGTH_SHORT).show()
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


        }


    }

}