package com.plugged.ui.patients

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.plugged.R
import com.plugged.databinding.FragmentProfileBinding
import com.plugged.models.LoginResponse
import com.plugged.utils.Constants.Companion.TAG
import com.plugged.viewmodel.PluggedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.profile_layout.*

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val viewModel: PluggedViewModel by viewModels()
    lateinit var binding: FragmentProfileBinding
    private  var doubleBackToExitPressedOnce = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPatient().observe(viewLifecycleOwner, Observer { patient ->

            if (patient  !=null)
            {
                setData(patient)
            }

        })
    }

    fun setData(patient:LoginResponse)
    {
        gender.text = patient.gender
        age.text = patient.age.toString()
        weight.text =patient.weight.toString()
        height.text = patient.height.toString()
        f_name.text = patient.firstname
        l_name.text = patient.lastname
        email.text = patient.email

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            if (doubleBackToExitPressedOnce) {
                activity?.let { ActivityCompat.finishAffinity(it) }
            }
            doubleBackToExitPressedOnce = true

            Toast.makeText(activity, "Please Click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)}

    }

}