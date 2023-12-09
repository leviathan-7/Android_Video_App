package com.example.neurodive_video

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Video : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video)
    }
    fun toMenu(view: View){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
    }
}