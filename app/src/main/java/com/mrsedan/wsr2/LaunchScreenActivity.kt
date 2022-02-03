package com.mrsedan.wsr2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule

class LaunchScreenActivity : AppCompatActivity() {
    lateinit var mSettings: SharedPreferences
    private fun getText(key:String):String{
        return mSettings.getString(key,"").toString()
    }
    private fun setText(key:String,v: String){
        mSettings.edit().putString(key,v).apply()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)
        mSettings = getSharedPreferences("settings", MODE_PRIVATE)
        Timer().schedule(1500){
            if (!getText("first").isEmpty()){
                startActivity(Intent(this@LaunchScreenActivity,LoginActivity::class.java))
                finishAffinity()
            } else {
                startActivity(Intent(this@LaunchScreenActivity,RegisterActivity::class.java))
                setText("first","net")
                finishAffinity()
            }

        }

    }
}