package com.example.neurodive_video

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.core.text.HtmlCompat
import androidx.core.view.children
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

var top = mutableListOf<String>()
var topClick2 = false
var ind = 0

class Video : AppCompatActivity() {
    lateinit var youtubePlayerView: YouTubePlayerView

    var videoID = ""
    var videoID1 = ""
    var goToNext = false

    override fun onCreate(savedInstanceState: Bundle?) {
        initMusicS()

        if (VideoPull.size < 1)
            initStartMusicPull()


        videoID = VideoPull[ind]
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video)

        youtubePlayerView = findViewById(R.id.youtube_player_view)

        lifecycle.addObserver(youtubePlayerView)

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                topClick2 = false
                if(!topClick)
                    youTubePlayer.loadVideo(videoID, 0f)
                else{
                    videoID = topID
                    topClick2 = true
                    youTubePlayer.loadVideo(videoID, 0f)
                    topClick = false
                }
            }

            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                if(state == PlayerConstants.PlayerState.ENDED)
                {
                    videoID = videoID1
                    onReady(youTubePlayer)
                }
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                if(goToNext){
                    goToNext = false
                    videoID = videoID1
                    onReady(youTubePlayer)
                }
            }
        })

        findViewById<TextView>(R.id.kol_text).setText("" + (ind+1) + " /" + VideoPull.size)
    }

    fun toSettings(view: View){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }
    fun toTop(view: View){
        val intent = Intent(this, Top::class.java)
        startActivity(intent)
    }

    fun like(view: View){
        if(addToTop()){
            val text = "Клип добавлен в избранное"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

            val et1: View = findViewById(R.id.imageView)
            et1.visibility = View.VISIBLE;
            et1.alpha = 0.75f;

            val timer = object: CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }
                override fun onFinish() {et1.visibility = View.INVISIBLE}
            }
            timer.start()
        }
    }

    fun next(view: View){
        goToNext = true
        changeMusic()
    }

    fun back(view: View){
        goToNext = true
        changeMusicBack()
    }

    private fun initStartMusicPull()
    {
        VideoPull = mutableListOf<String>()
        for(key in MusicLists.keys)
            VideoPull += MusicLists[key] !!
    }

    private fun changeMusic(){
        if(topClick2)
        {
            topClick2 = false
        }
        ind += 1
        if (ind >= VideoPull.size)
            ind = 0
        videoID1 = VideoPull[ind]

        findViewById<TextView>(R.id.kol_text).setText("" + (ind+1) + " /" + VideoPull.size)
    }

    private fun changeMusicBack(){
        if(topClick2)
        {
            topClick2 = false
        }
        ind -= 1
        if (ind < 0)
            ind = 0
        videoID1 = VideoPull[ind]

        findViewById<TextView>(R.id.kol_text).setText("" + (ind+1) + " /" + VideoPull.size)
    }

    private fun addToTop(): Boolean {
        if (videoID !in top){
            top.add(videoID)
            return true
        }
        else
        {
            val text = "Это видео вам уже нравится!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            return false
        }
    }
}