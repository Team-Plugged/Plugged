package com.plugged.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.plugged.R
import com.plugged.ui.home.HospitalActivity
import com.plugged.ui.home.PatientActivity
import com.plugged.utils.MyPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Check Whether a user Logged in
        when(MyPreferences(this).logged_in)
        {
            true->{
                 if(MyPreferences(this).is_staff)
                 {
                     startActivity(Intent(this,HospitalActivity::class.java))
                 }
                else{
                     startActivity(Intent(this,PatientActivity::class.java))

                 }
            }


        }

        btn_getStarted.setOnClickListener {
            startActivity(Intent(this, TutorialActivity::class.java))
        }
    }
}

