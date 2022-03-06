package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.MovieDetails
import com.example.movieapp.models.Result
import com.example.movieapp.repository.MoviesRepository
import com.example.movieapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel(val repository: MoviesRepository):ViewModel() {
    var popularMovies:MutableLiveData<MovieDetails> = MutableLiveData()
    var moviesResponse:MovieDetails?=null

    var nowPlayingMovies:MutableLiveData<MovieDetails> = MutableLiveData()
    var moviesResponse1:MovieDetails?=null

    val searchMovies:MutableLiveData<MovieDetails> = MutableLiveData()
    var searchResponse:MovieDetails?=null

    init {
        getPopularMovies()
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() = viewModelScope.launch {
        val response = repository.getNowShowingMovies()
        nowPlayingMovies.postValue(handleNowPlayingMovies(response))
    }

    private fun handleNowPlayingMovies(response: Response<MovieDetails>): MovieDetails? {
        if(response.isSuccessful){
            response.body()?.let {
                    resultResponse ->
                moviesResponse = resultResponse
                return resultResponse
            }
        }
        return null
    }

    private fun handlePopularMoviesResponse(response: Response<MovieDetails>): MovieDetails? {
        if(response.isSuccessful){
            response.body()?.let {
                    resultResponse ->
                moviesResponse1 = resultResponse
                return moviesResponse1?:resultResponse
            }
        }
        return null
    }

    private fun getPopularMovies()= viewModelScope.launch {
        val response = repository.getPopularMovies()
        popularMovies.postValue(handlePopularMoviesResponse(response))
    }

    fun getSearchMovies(search : String) = viewModelScope.launch {
        val response = repository.getSearchMovies(search)
        searchMovies.postValue(handleSearchMoviesResponse(response))
    }

    private fun handleSearchMoviesResponse(response: Response<MovieDetails>): MovieDetails? {
        if(response.isSuccessful){
            response.body()?.let {
                    resultResponse ->
                searchResponse = resultResponse
                return resultResponse
            }
        }
        return null
    }


    fun saveMovie(movie: Result) = viewModelScope.launch {
        repository.upsert(movie)
    }
    fun getSavedMovies() = repository.getSavedMovies()
    fun deleteMovie(movie: Result) = viewModelScope.launch {
        repository.deleteMovie(movie)
    }
}