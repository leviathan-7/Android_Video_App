package com.example.neurodive_video

import android.content.Intent
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
import androidx.core.text.HtmlCompat
import androidx.core.view.children
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

var top = mutableListOf<String>()
var topClick2 = false

class Video : AppCompatActivity() {
    lateinit var youtubePlayerView: YouTubePlayerView

    // on below line we are creating a
    // string variable for our video id.
    var videoID = "vG2PNdI8axo"
    var videoID1 = "S0Q4gqBUs7c"
    var goToNext = false
    private var ind = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        println("----" + repository!!.getVideos())

        initMusicS()
        if (VideoPull.size < 1)
            initStartMusicPull()
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
    }

    fun toMenu(view: View){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
    }

    fun like(view: View){
        addToTop()
        val text = "Видео добавлено в понравившиеся"
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

    fun next(view: View){

        goToNext = true
        changeMusic()
    }

    private fun initStartMusicPull()
    {
        VideoPull = mutableListOf<String>()
        VideoPull += "vG2PNdI8axo"
        VideoPull += "S0Q4gqBUs7c"
        for(key in MusicLists.keys)
            VideoPull += MusicLists[key] !!
        //VideoPull = MusicLists["Рок"]!!
    }

    private fun changeMusic(){
        if(topClick2)
        {
            ind = VideoPull.indexOf(topID)
            topClick2 = false
        }
        ind += 1
        if (ind >= VideoPull.size)
            ind = 0
        videoID1 = VideoPull[ind]
    }

    private fun changeMusic2(){
        if (videoID ==  "S0Q4gqBUs7c")
            videoID1 = "vG2PNdI8axo"
        else
            videoID1 = "S0Q4gqBUs7c"
    }

    private fun addToTop(){
        if (videoID !in top)
            top.add(videoID)
        else
        {
            val text = "Это видео вам уже нравится!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
    }
}