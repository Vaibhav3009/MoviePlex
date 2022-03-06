package com.example.movieapp.api

import com.example.movieapp.api.MovieApi
import com.example.movieapp.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetroFitInstance {
    companion object{
        private val retrofit by lazy {
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        val api by lazy{
            retrofit.create(MovieApi::class.java)
        }
    }
}