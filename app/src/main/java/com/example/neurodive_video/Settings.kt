package com.example.neurodive_video

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children

var automaticBrowsing = false
var genres = mutableListOf<String>()

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
    }
    fun toVideo(view: View){
        val intent = Intent(this, Video::class.java)
        startActivity(intent)
    }
    fun autoBrowse(view: View){
        automaticBrowsing = !automaticBrowsing
    }
    fun chosenGenres(view: View){
        val id = view.id
        val name = resources.getResourceName(id)
        val rows = findViewById<LinearLayout>(R.id.linearLayout).children
        var text = ""
        for (row in rows) {
            val table_row = findViewById<TableRow>(row.id)
            val textView = table_row.getChildAt(0) as TextView
            text = textView.text.toString()
            val name2 = resources.getResourceName(textView.id)
            val num1 = name[name.length - 1].toInt()
            val num2 = name2[name2.length - 1].toInt()
            val substr = "TextView"
            if (substr in name2 && num2 == num1) {
                var checkBox = findViewById<CheckBox>(id)
                if (checkBox.isChecked)
                    genres.add(text)
                else
                    genres.remove(text)
                break
            }
        }
    }
    fun toMenu(view: View){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
    }
    fun toBack(view: View){
        finish()
    }
}