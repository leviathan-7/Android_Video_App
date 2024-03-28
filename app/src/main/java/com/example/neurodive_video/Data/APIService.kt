package com.example.neurodive_video.Data

import com.example.neurodive_video.POJO.VideoNote
import retrofit2.http.GET


interface APIService {
    @GET("videos")
    suspend fun loadVideos(): List<VideoNote>?

}