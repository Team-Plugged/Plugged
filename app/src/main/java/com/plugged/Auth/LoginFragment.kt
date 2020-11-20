package com.plugged.Auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.plugged.R
import com.plugged.home.HospitalActivity
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : DialogFragment() {


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
                   user = "Hospital"
                }
            }

        }

        view.login.setOnClickListener {

            startActivity(Intent(activity,HospitalActivity::class.java))

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

