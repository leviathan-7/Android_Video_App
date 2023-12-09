package com.example.neurodive_video

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun toVideo(view: View){
        val intent = Intent(this, Video::class.java)
        startActivity(intent)
    }
    fun toMenu(view: View){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
    }
}