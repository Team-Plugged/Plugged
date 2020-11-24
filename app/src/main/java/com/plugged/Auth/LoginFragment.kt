package com.plugged.Auth
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.plugged.utils.MyPreferences
import com.plugged.R
import com.plugged.ui.home.PatientActivity
import com.plugged.models.Login
import com.plugged.ui.home.HospitalActivity
import com.plugged.utils.Constants
import com.plugged.utils.Resource
import com.plugged.viewmodel.PluggedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.edit_email
import kotlinx.android.synthetic.main.fragment_login.edit_password
import kotlinx.android.synthetic.main.fragment_login.view.*

@AndroidEntryPoint
class LoginFragment : DialogFragment() {
    private val viewModel: PluggedViewModel by viewModels()

    companion object {
        const val TAG = "Login"
        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: String = "", subTitle: String = ""): LoginFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.spinner_login
        val progress_bar = view.progressBar
        var user = "Patient"
        val user_type = resources.getStringArray(R.array.user_type)
        if (spinner != null){
            val adapter =
                activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, user_type) }
            if (adapter != null) {
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            }
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    user = parent.getItemAtPosition(position).toString()

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                   user = "Patient"
                }
            }

        }

        view.btn_create.setOnClickListener {

            startActivity(Intent(activity,HospitalActivity::class.java))


            if(edit_email.text.isNullOrEmpty())
            {
                edit_email.error = "Please input your email"
                edit_email.requestFocus()
                return@setOnClickListener
            }

            if (edit_password.text.isNullOrEmpty())
            {
                edit_password.error = "Please input your Password"
                edit_password.requestFocus()
                return@setOnClickListener
            }
            val email = edit_email.text.toString()
            val password = edit_password.text.toString()

            val Login = Login(email,password)
            Log.d(Constants.TAG,Login.toString())

            if (user == "Patient")
            {
                viewModel.LoginPatient(Login)

            }

            else{
                Log.d(TAG,"Hospital Selected")

                viewModel.LoginHospital(Login)

            }


            viewModel.loginResponse.observe(viewLifecycleOwner, Observer {response->

                when(response)
                {

                    is Resource.Loading -> {

                        progress_bar.visibility=View.VISIBLE
                    }

                    is Resource.Success->{
                        progress_bar.visibility = View.INVISIBLE
                       response.data?.let {Patient_Data->

                           viewModel.savePatient(Patient_Data)
                            MyPreferences(activity).is_staff = false
                           MyPreferences(activity).logged_in = true
                           Log.d(TAG,Patient_Data.toString())
                           startActivity(Intent(activity,PatientActivity::class.java))



                       }

                    }

                    is Resource.Error -> {
                        progress_bar.visibility = View.INVISIBLE

                        response.message?.let {
                            Toast.makeText(activity, "Error Occured: $it", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }


            })

            viewModel.loginHospital.observe(viewLifecycleOwner, Observer {response->

                when(response)
                {

                    is Resource.Loading -> {

                        progress_bar.visibility=View.VISIBLE
                    }

                    is Resource.Success->{
                        progress_bar.visibility = View.INVISIBLE
                        response.data?.let {staff_data->

                            MyPreferences(activity).is_staff = true
                            MyPreferences(activity).logged_in = true
                            Log.d(TAG,staff_data.toString())
                            startActivity(Intent(activity,HospitalActivity::class.java))



                        }

                    }

                    is Resource.Error -> {
                        progress_bar.visibility = View.INVISIBLE

                        response.message?.let {
                            Toast.makeText(activity, "Error Occured: $it", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }


            })


        }

        view.register.setOnClickListener {
            activity?.supportFragmentManager?.let { it1 ->
                RegistrationFragment.newInstance().show(
                    it1, RegistrationFragment.TAG)
            }
            dismiss()


        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }
}

