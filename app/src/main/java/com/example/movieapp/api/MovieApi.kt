package com.example.movieapp.api

import com.example.movieapp.models.MovieDetails
import com.example.movieapp.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey:String = API_KEY
    ): Response<MovieDetails>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key")
        apiKey:String = API_KEY
    ): Response<MovieDetails>


    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query")searchQuery:String,
        @Query("api_key")
        apiKey:String = API_KEY,
    ): Response<MovieDetails>
}
