package com.example.neurodive_video

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
    }
    fun toSettings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }
    fun toVideo(view: View){
        val intent = Intent(this, Video::class.java)
        startActivity(intent)
    }
    fun toTop(view: View){
        val intent = Intent(this, Top::class.java)
        startActivity(intent)
    }
    fun toBack(view: View){
        finish()
    }
}