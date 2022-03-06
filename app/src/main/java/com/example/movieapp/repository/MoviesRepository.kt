package com.example.movieapp.repository

import com.example.movieapp.api.RetroFitInstance
import com.example.movieapp.db.MovieDatabase
import com.example.movieapp.models.Result


class MoviesRepository( val db:MovieDatabase) {
        suspend fun getPopularMovies() =
            RetroFitInstance.api.getPopularMovies()

        suspend fun getNowShowingMovies() = RetroFitInstance.api.getNowPlayingMovies()

        suspend fun getSearchMovies(searchQuery:String) = RetroFitInstance.api.searchMovies(searchQuery)

        suspend fun upsert(movie:Result) = db.getMovieDao().upsert(movie)
        fun getSavedMovies() = db.getMovieDao().getAllResults()
        suspend fun deleteMovie(movie: Result) = db.getMovieDao().deleteResult(movie)
    }
