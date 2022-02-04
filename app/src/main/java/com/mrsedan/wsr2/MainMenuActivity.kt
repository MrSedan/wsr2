package com.mrsedan.wsr2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class MainMenuActivity : AppCompatActivity() {
    lateinit var mSettings: SharedPreferences
    private fun getText(key:String):String{
        return mSettings.getString(key,"").toString()
    }
    private fun setText(key:String,v: String){
        mSettings.edit().putString(key,v).apply()
    }
    private fun drawCover(){
        var que = Volley.newRequestQueue(this)
        val req = JsonObjectRequest(Request.Method.GET,"http://cinema.areas.su/movies/cover",null,
            {
                val pic = findViewById<ImageView>(R.id.coverImage)
                Glide.with(this)
                    .load("http://cinema.areas.su/up/images/"+it.getString("backgroundImage"))
                    .into(pic)
            },{})
        que.add(req)
    }

    private fun drawLastWatched(){
        val pic = findViewById<ImageView>(R.id.youWatchedPic)
        val name = findViewById<TextView>(R.id.youWatchedName)
        val que = Volley.newRequestQueue(this)
        val token = getText("token")
        val req =object : JsonArrayRequest(Request.Method.GET,"http://cinema.areas.su/usermovies?filter=lastView",null,
            {
                val item = it.getJSONObject(0)
                Glide.with(this)
                    .load("http://cinema.areas.su/up/images/"+item.getString("poster"))
                    .into(pic)
                name.text = item.getString("name")
            },{
                Log.e("Aboba",it.toString())
            }) {
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> =
                    HashMap()
                headers["Content-Type"] = "application/json"
                headers["Accept"] = "application/json"
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }
        que.add(req)
    }

    private fun drawProfile(){
        val pic = findViewById<ImageView>(R.id.userAvatar)
        val userName = findViewById<TextView>(R.id.name)
        val email = findViewById<TextView>(R.id.email)
        val que = Volley.newRequestQueue(this)
        val token = getText("token")
        val req =object : JsonArrayRequest(Request.Method.GET,"http://cinema.areas.su/user",null,
            {
                val item = it.getJSONObject(0)
                Glide.with(this)
                    .load("http://cinema.areas.su/up/images/"+item.getString("avatar"))
                    .into(pic)
                val name = item.getString("firstName")+" "+item.getString("lastName")
                userName.text = name
                email.text = item.getString("email")
            },{
                Log.e("Aboba",it.toString())
            }) {
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> =
                    HashMap()
                headers["Content-Type"] = "application/json"
                headers["Accept"] = "application/json"
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }
        que.add(req)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSettings = getSharedPreferences("settings", MODE_PRIVATE)
        setContentView(R.layout.activity_main_menu)
        drawCover()
        findViewById<ImageButton>(R.id.exitButton).setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finishAffinity()
        }
        drawProfile()
        drawLastWatched()
    }
}