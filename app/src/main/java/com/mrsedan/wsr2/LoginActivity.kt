package com.mrsedan.wsr2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    lateinit var mSettings: SharedPreferences
    private fun getText(key:String):String{
        return mSettings.getString(key,"").toString()
    }
    private fun setText(key:String,v: String){
        mSettings.edit().putString(key,v).apply()
    }
    private fun login(){
        val que = Volley.newRequestQueue(this)
        val obj = JSONObject()
        obj.put("email",findViewById<EditText>(R.id.email).text.toString())
        obj.put("password",findViewById<EditText>(R.id.password).text.toString())
        val req = JsonObjectRequest(Request.Method.POST,"http://cinema.areas.su/auth/login",obj,
            {
                startActivity(Intent(this,MainMenuActivity::class.java))
                setText("token",it.getString("token"))
                finishAffinity()
            },{
                Log.e("Aboba",it.toString())
            })
        que.add(req)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSettings = getSharedPreferences("settings", MODE_PRIVATE)
        setContentView(R.layout.activity_login)
        findViewById<ConstraintLayout>(R.id.loginButton).setOnClickListener{
            login()
        }
    }
}