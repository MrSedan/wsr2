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
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private fun tryRegex(){
        val regex = """[0-9a-z]+@[0-9a-z]+\.[a-z]{1,3}""".toRegex()
        Log.d("Aboba",regex.matches("vasya@mail.com").toString())
    }
    private fun login(){
        val fname = findViewById<EditText>(R.id.fname).text.toString()
        val sname = findViewById<EditText>(R.id.sname).text.toString()
        val email = findViewById<EditText>(R.id.email).text.toString()
        val pass = findViewById<EditText>(R.id.password).text.toString()
        val repass = findViewById<EditText>(R.id.rePassword).text.toString()
        val regex = """[0-9a-z]+@[0-9a-z]+\.[a-z]{1,3}""".toRegex()
        val regexBool = regex.matches(email)
        if (!regexBool || pass!=repass || pass.isEmpty()){
            Toast.makeText(this,"Облом",Toast.LENGTH_SHORT).show()
            return
        }
        val que = Volley.newRequestQueue(this)
        val obj = JSONObject()
        obj.put("firstName",fname)
        obj.put("secondName",sname)
        obj.put("email",email)
        obj.put("password",pass)
        val req = JsonObjectRequest(Request.Method.POST,"http://cinema.areas.su/auth/register",obj,
            {
                try{
                    Toast.makeText(this,"Успешная регистрация",Toast.LENGTH_LONG).show()
                } catch (e:JSONException){
                    Toast.makeText(this,"Успешная регистрация",Toast.LENGTH_LONG).show()
                }
            },{
                if(it.networkResponse.statusCode==201){
                    Toast.makeText(this,"Успешная регистрация",Toast.LENGTH_LONG).show()
                } else{
                    Toast.makeText(this,"Облом у сервера",Toast.LENGTH_LONG).show()
                    Log.e("Aboba",it.toString())
                }
            })
        que.add(req)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewById<Button>(R.id.goToLoginButton).setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
        findViewById<ConstraintLayout>(R.id.registerButton).setOnClickListener{
            login()
        }


        /*val que = Volley.newRequestQueue(this)
        val req = object: JsonArrayRequest(Request.Method.PUT,"http://cinema.areas.su/user",null,
            {
                Log.d("Aboba",it.toString())
            },
            {
                Log.e("Aboba",it.toString())
            }) {
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> =
                    HashMap()
                headers["Content-Type"] = "application/json"
                headers["Accept"] = "application/json"
                headers["Authorization"] = "Bearer 615108"
                return headers
            }
        }
        que.add(req)*/
    }
}