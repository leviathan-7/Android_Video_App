package com.example.neurodive_video.Data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class AppRepository() {
    private var hashVideos: HashMap<String, List<String>> = HashMap()

    private var apiService = Retrofit.Builder()
        .baseUrl("https://66054e6c2ca9478ea17ffe49.mockapi.io/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
        .create(APIService::class.java)

    init {
        getVideos()
    }

    fun getVideos(): HashMap<String, List<String>> {
        if (hashVideos.isEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                var lst = apiService.loadVideos();
                if (lst != null){
                    for(note in lst){
                        hashVideos.put(note.style!!, note.videos!!)
                    }
                }
            }
        }
        return hashVideos
    }

}
