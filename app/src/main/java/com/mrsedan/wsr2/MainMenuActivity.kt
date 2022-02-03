package com.mrsedan.wsr2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class MainMenuActivity : AppCompatActivity() {
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        drawCover()
    }
}