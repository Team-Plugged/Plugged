package com.plugged

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.plugged.ui.TutorialActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_getStarted.setOnClickListener {
            startActivity(Intent(this, TutorialActivity::class.java))
        }
    }
}

