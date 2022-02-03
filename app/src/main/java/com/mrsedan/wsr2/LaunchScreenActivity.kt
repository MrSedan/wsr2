package com.mrsedan.wsr2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule

class LaunchScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)
        Timer().schedule(1500){
            startActivity(Intent(this@LaunchScreenActivity,LoginActivity::class.java))
            finishAffinity()
        }
    }
}