package com.example.neurodive_video

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.neurodive_video.Data.AppRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = AppRepository()
        setContentView(R.layout.activity_main)
    }
    fun toCalibr(view: View){
        val intent = Intent(this, Calibr::class.java)
        startActivity(intent)
    }
}

var repository: AppRepository? = null