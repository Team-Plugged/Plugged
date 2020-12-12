package com.plugged.ui.patients

import android.os.Bundle
import android.os.Handler
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
import com.plugged.utils.bindImageUrl
import com.plugged.viewmodel.PluggedViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_layout.email
import kotlinx.android.synthetic.main.profile_layout.f_name
import kotlinx.android.synthetic.main.profile_layout.gender
import kotlinx.android.synthetic.main.profile_layout.height
import kotlinx.android.synthetic.main.profile_layout.l_name
import kotlinx.android.synthetic.main.profile_layout.text_dob
import kotlinx.android.synthetic.main.profile_layout.weight

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

        binding.gender.text = patient.gender
        gender.text = patient.gender
        binding.weight.text =patient.weight.toString()
        binding.height.text = patient.height.toString()
        binding.fName.text = patient.firstname
        binding.lName.text = patient.lastname
        binding.email.text = patient.email
        binding.textDob.text = patient.dateOfBirth
        binding.textPhone.text = patient.contactInfo
        binding.address.text = patient.address

        Picasso.get()
            .load(patient.image)
            .into(binding.profileImage)


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