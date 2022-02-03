package com.mrsedan.wsr2

import android.content.Intent
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
    private fun login(){
        val que = Volley.newRequestQueue(this)
        val obj = JSONObject()
        obj.put("email",findViewById<EditText>(R.id.email).text.toString())
        obj.put("password",findViewById<EditText>(R.id.password).text.toString())
        val req = JsonObjectRequest(Request.Method.POST,"http://cinema.areas.su/auth/login",obj,
            {
               Toast.makeText(this,it.getString("token"),Toast.LENGTH_LONG).show()
                startActivity(Intent(this,MainMenuActivity::class.java))
                finishAffinity()
            },{
                Log.e("Aboba",it.toString())
            })
        que.add(req)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.goToRegisterButton).setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        findViewById<ConstraintLayout>(R.id.loginButton).setOnClickListener{
            login()
        }
    }
}