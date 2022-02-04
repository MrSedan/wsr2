package com.mrsedan.wsr2

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class DiscussActivity : AppCompatActivity() {
    lateinit var mSettings: SharedPreferences
    private fun getText(key:String):String{
        return mSettings.getString(key,"").toString()
    }
    private fun setText(key:String,v: String){
        mSettings.edit().putString(key,v).apply()
    }
    private fun drawDiscuss(){
        val container = findViewById<LinearLayout>(R.id.container)
        val token = getText("token")
        val que = Volley.newRequestQueue(this)
        val req = object: JsonArrayRequest(
            Request.Method.GET,"http://cinema.areas.su/user/chats",null,
            {
                for (i in 0 until it.length()){
                    val item = it.getJSONObject(i)
                    val name = item.getString("name")
                    val newView = layoutInflater.inflate(R.layout.chat,null)
                    newView.findViewById<TextView>(R.id.filmName).text = name
                    container.addView(newView)
                }
            },
            {}) {
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
        setContentView(R.layout.activity_discuss)
        drawDiscuss()
    }
}