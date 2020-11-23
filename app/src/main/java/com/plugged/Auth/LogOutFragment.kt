package com.plugged.Auth

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.plugged.R


class LogOutFragment : Fragment() {

    val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            activity?.let { ActivityCompat.finishAffinity(it) }     }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_out, container, false)
    }

}