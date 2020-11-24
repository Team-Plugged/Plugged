package com.plugged.Auth
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.plugged.R
import com.plugged.models.RegisterHospital
import com.plugged.ui.home.PatientActivity
import com.plugged.utils.MyPreferences
import com.plugged.utils.Resource
import com.plugged.viewmodel.PluggedViewModel
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*

class RegistrationFragment : DialogFragment() {
    private val viewModel: PluggedViewModel by viewModels()


    companion object {
        const val TAG = "Registration"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: String = "", subTitle: String = ""): RegistrationFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = RegistrationFragment()
            fragment.arguments = args
            return fragment
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.btn_create.setOnClickListener {

            if (edit_name.text.isNullOrEmpty())
            {
                edit_name.error = "Please input your Hospital Name"
                edit_name.requestFocus()
                return@setOnClickListener
            }
            if (edit_email.text.isNullOrEmpty())
            {
                edit_email.error = "Please input your Email"
                edit_email.requestFocus()
                return@setOnClickListener
            }
            if (edit_location.text.isNullOrEmpty())
            {
                edit_location.error = "Please input your Hospital Location"
                edit_location.requestFocus()
                return@setOnClickListener
            }
            if (edit_password.text.isNullOrEmpty())
            {
                edit_password.error = "Please input your Password"
                edit_password.requestFocus()
                return@setOnClickListener
            }

            val email = edit_email.text.toString()
            val location = edit_location.text.toString()
            val hospital_name = edit_name.text.toString()
            val password = edit_password.text.toString()

            val hospital_registration = RegisterHospital(location,email,hospital_name,password)
            viewModel.RegisterHospital(hospital_registration)
            viewModel.registerHospital.observe(viewLifecycleOwner, Observer {response->

                when(response)
                {

                    is Resource.Loading -> {

                        progressBar.visibility=View.VISIBLE
                    }

                    is Resource.Success->{
                        progressBar.visibility = View.INVISIBLE
                        response.data?.let {staff_data->

                            MyPreferences(activity).is_staff = true
                            MyPreferences(activity).logged_in = true
                            Log.d(TAG,staff_data.toString())
                            startActivity(Intent(activity, PatientActivity::class.java))



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

        view.sign_in.setOnClickListener {
            activity?.supportFragmentManager?.let { it1 -> LoginFragment.newInstance().show(it1,LoginFragment.TAG) }
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

}