package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieVerticalClick
import com.example.movieapp.adapter.MovieVerticalListAdapter
import com.example.movieapp.models.Result
import com.example.movieapp.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_bookmarked_movie.*


class BookmarkedMovie : Fragment(),MovieVerticalClick {

    lateinit var viewModel: MoviesViewModel
    lateinit var mAdapter: MovieVerticalListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmarked_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        viewModel.getSavedMovies().observe(viewLifecycleOwner, Observer {
            if(it?.size == 0){
                movieContainer.visibility = View.GONE
                nothing.visibility = View.VISIBLE
            }else{
                movieContainer.visibility = View.VISIBLE
                nothing.visibility = View.GONE
                mAdapter = MovieVerticalListAdapter(this,it)
                movieContainer.adapter = mAdapter
                movieContainer.layoutManager = LinearLayoutManager(activity)
            }
        })

    }

    override fun onItemClicked(movie: Result?) {
        val bundle = Bundle()
        bundle.putSerializable("result",movie)
        findNavController().navigate(R.id.action_bookmarkedMovie_to_movieDetails,bundle)
    }
}