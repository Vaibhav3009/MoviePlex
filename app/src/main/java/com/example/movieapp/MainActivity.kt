package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.db.MovieDatabase
import com.example.movieapp.repository.MoviesRepository
import com.example.movieapp.viewmodel.MoviesViewModel
import com.example.movieapp.viewmodel.MoviesViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();
        val repository = MoviesRepository(MovieDatabase.invoke(this))
        val viewModelProviderFactory = MoviesViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(MoviesViewModel::class.java)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.moviesNavHost) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)
    }
}