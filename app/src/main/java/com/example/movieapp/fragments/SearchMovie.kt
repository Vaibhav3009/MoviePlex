package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieVerticalClick
import com.example.movieapp.adapter.MovieVerticalListAdapter
import com.example.movieapp.models.Result
import com.example.movieapp.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_bookmarked_movie.*
import kotlinx.android.synthetic.main.fragment_search_movie.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchMovie : Fragment(),MovieVerticalClick{

    lateinit var viewModel: MoviesViewModel
    lateinit var mAdapter: MovieVerticalListAdapter
    var list: List<Result>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        var job: Job? = null
        etSearch.addTextChangedListener {editable->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                if(editable!=null){
                    if(editable.toString().isNotEmpty()){
                        viewModel.getSearchMovies(editable.toString())
                    }
                }
            }
        }
        viewModel.searchMovies.observe(viewLifecycleOwner, Observer {
            response ->
            list = response.results
            if(list?.size?:0>0) {
                mAdapter = MovieVerticalListAdapter(this, list)
                rvSearchMovie.adapter = mAdapter
                rvSearchMovie.layoutManager = LinearLayoutManager(activity)
            }

        })
    }
    override fun onItemClicked(movie: Result?) {
        val bundle = Bundle()
        bundle.putSerializable("result",movie)
        findNavController().navigate(R.id.action_searchMovie_to_movieDetails,bundle)
    }


}