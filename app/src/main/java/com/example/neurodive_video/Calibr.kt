package com.example.neurodive_video

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Calibr : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calibr)
    }
    fun toVideo(view: View){
        val intent = Intent(this, Video::class.java)
        startActivity(intent)
    }
}

