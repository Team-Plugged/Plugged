package com.plugged.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.plugged.Auth.LoginFragment
import com.plugged.Auth.RegistrationFragment
import com.plugged.ContactUsFragment
import com.plugged.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TutorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
//            R.id.mission -> {
//                LoginFragment.newInstance().show(supportFragmentManager, LoginFragment.TAG)
//                RegisterPatient.newInstance()
//                    .show(supportFragmentManager, RegisterPatient.TAG)
//
//                true
//            }
            R.id.auth -> {
                LoginFragment.newInstance().show(supportFragmentManager, LoginFragment.TAG)

                true

            }
            R.id.about -> {
                ContactUsFragment.newInstance().show(supportFragmentManager, ContactUsFragment.TAG)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}