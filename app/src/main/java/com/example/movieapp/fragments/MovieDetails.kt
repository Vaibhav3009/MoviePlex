package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.models.MovieDetails
import com.example.movieapp.viewmodel.MoviesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetails : Fragment() {

    lateinit var viewModel: MoviesViewModel
   val args:com.example.movieapp.fragments.MovieDetailsArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val movie = args.result
        Glide.with(this).load("https://image.tmdb.org/t/p/original/${movie?.posterPath}").into(movie_poster)
        title.text = movie.title
        release_date.text = movie.releaseDate
        movie_rating.text = movie.voteAverage.toString()
        language.text = movie.originalLanguage
        movie_overview.text = movie.overview
        fab.setOnClickListener {
            viewModel.saveMovie(movie)
            Snackbar.make(view,"Movie added to bookmarks", Snackbar.LENGTH_SHORT).show()
        }
    }

}
