package com.example.neurodive_video.POJO

import com.google.gson.annotations.SerializedName

class VideoNote {
    @SerializedName("videos")
    var videos: List<String>? = null

    @SerializedName("style")
    var style: String? = null

    @SerializedName("id")
    var id: String? = null
}
