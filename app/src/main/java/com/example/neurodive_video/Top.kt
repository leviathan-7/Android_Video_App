package com.example.neurodive_video

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.marginRight
import androidx.compose.foundation.*
import androidx.compose.ui.unit.dp

var topClick = false
var topID = ""

class Top : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top)
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
                /*var heightInDp = 54 // высота текстового поля в DP
                text.height = (heightInDp * density).toInt()*/

                text.text = video
                text.setTextColor(Color.parseColor("#000000"))
                text.textSize = 20F
                text.setTypeface(null, Typeface.BOLD)
                text.gravity = Gravity.CENTER
                text.setOnClickListener{
                    showTopVideo(text, video)
                }

                /*var shape = GradientDrawable()
                shape.setStroke(5, Color.BLACK) // Установка толщины и цвета границы
                text.background = shape*/

                //Меняем параметры кнопки
                var button = Button(this) // Создаем кнопку для строки
                button.text = "Иск"
                button.textSize = 15f
                button.setTextColor(Color.parseColor("#000000"))
                button.setOnClickListener{
                    toMenu(button)
                }
                button.setBackgroundColor(Color.LTGRAY)
                var shape = GradientDrawable()
                shape.setStroke(5, Color.BLACK) // Установка толщины и цвета границы
                button.background = shape

                /*heightInDp = 54 // высота кнопки в DP
                button.height = (heightInDp * density).toInt()*/

                // Добавляем виджеты в строку
                tableRow.addView(text)
                tableRow.addView(button)
                // Меняем параметры строки
                tableRow.setBackgroundColor(Color.parseColor("#FFFFFF"))
                // Добавляем строку в TableLayout
                findViewById<TableLayout>(R.id.tableTop).addView(tableRow)
            }
        }

    }
    fun toVideo(view: View){
        val intent = Intent(this, Video::class.java)
        startActivity(intent)
    }
    fun toMenu(view: View){
        val intent = Intent(this, Menu::class.java)
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