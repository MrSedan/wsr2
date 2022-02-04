package com.mrsedan.wsr2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject

class MainMenuActivity : AppCompatActivity() {
    lateinit var mSettings: SharedPreferences
    private fun getText(key:String):String{
        return mSettings.getString(key,"").toString()
    }
    private fun setText(key:String,v: String){
        mSettings.edit().putString(key,v).apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSettings = getSharedPreferences("settings", MODE_PRIVATE)
        setContentView(R.layout.activity_main_menu)
        findViewById<ImageButton>(R.id.discButton).setOnClickListener {
            startActivity(Intent(this,DiscussActivity::class.java))
        }
    }
}