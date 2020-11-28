package com.plugged.ui.home

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.plugged.R
import com.plugged.utils.MyPreferences
import com.plugged.viewmodel.PluggedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HospitalActivity : AppCompatActivity() {
    private val viewModel: PluggedViewModel by viewModels()

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        setupDrawerLayout()

        val logOut = navView.menu.findItem(R.id.logOut)
        logOut.setOnMenuItemClickListener {

            viewModel.deleteToken()
            MyPreferences(this).is_staff = false
            MyPreferences(this).logged_in = false
            this.finishAffinity()
            return@setOnMenuItemClickListener true
        }

    }

    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
            }

            this.doubleBackToExitPressedOnce = true
            makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        } else {
            super.onBackPressed()
        }
    }


    private fun setupDrawerLayout() {
        navView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }


}