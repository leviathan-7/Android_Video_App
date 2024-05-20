package com.example.neurodive_video

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

var topClick = false
var topID = ""

class Top : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top)
        loadTop()
    }
    fun loadTop(){
        if(top.size == 0)
            findViewById<TableLayout>(R.id.tableTop).setBackgroundColor(Color.parseColor("#FFFFFF"))
        else{
            for(video in top){
                var tableRow = TableRow(this) // Создаем новую строку
                var text = TextView(this) // Создаем текстовое поле для строки
                //Меняем параметры текстового поля
                val widthInDp = 215 // ширина текстового поля в DP
                val density = resources.displayMetrics.density
                text.width = (widthInDp * density).toInt()

                text.text = video
                text.setTextColor(Color.parseColor("#FFFFFF"))
                text.textSize = 20F
                text.setTypeface(null, Typeface.BOLD)
                text.gravity = Gravity.CENTER
                text.setOnClickListener{
                    showTopVideo(text, video)
                }

                //Меняем параметры кнопки
                var button = Button(this) // Создаем кнопку для строки
                button.text = "\uD83D\uDDD1\uFE0F"
                button.textSize = 25f
                button.setTextColor(Color.parseColor("#FFFFFF"))
                button.setOnClickListener{
                    delTopVideo(button, video)
                }
                button.setBackgroundColor(Color.LTGRAY)
                var shape = GradientDrawable()
                //shape.setStroke(5, Color.BLACK) // Установка толщины и цвета границы
                button.background = shape

                // Добавляем виджеты в строку
                tableRow.addView(text)
                var t = TextView(this)
                t.text = "                 "
                tableRow.addView(t)
                tableRow.addView(button)
                // Меняем параметры строки
                tableRow.setBackgroundColor(Color.parseColor("#292727"))
                // Добавляем строку в TableLayout
                findViewById<TableLayout>(R.id.tableTop).addView(tableRow)
            }
        }
    }
    fun delTopVideo(view: View, video: String){
        top.remove(video)
        findViewById<TableLayout>(R.id.tableTop).removeAllViews()
        loadTop()
    }
    fun toVideo(view: View){
        val intent = Intent(this, Video::class.java)
        startActivity(intent)
    }
    fun toSettings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }
    fun toBack(view: View){
        finish()
    }

    fun showTopVideo(view: View, videoID: String){
        topClick = true
        topID = videoID
        val intent = Intent(this, Video::class.java)
        startActivity(intent)
    }
}