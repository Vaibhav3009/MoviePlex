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
import com.bumptech.glide.Glide
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieClick
import com.example.movieapp.adapter.NowPlayingAdapter
import com.example.movieapp.models.Result
import com.example.movieapp.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.movie_item.*
import kotlinx.android.synthetic.main.movie_item.view.*

class Home : Fragment(), MovieClick {

    lateinit var viewModel: MoviesViewModel
    lateinit var mAdapter: NowPlayingAdapter
    lateinit var popAdapter:NowPlayingAdapter
    var list: List<Result>? = null
    var bannerItem:Result?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()

    }
  fun setBannerItem(){
      val size = list?.size?:0
      val rand = (0 until size).random()
      Glide.with(this).load("https://image.tmdb.org/t/p/original/${list?.get(rand)?.posterPath}").into(banner)
      bannerItem = list?.get(rand)
  }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        viewModel.nowPlayingMovies.observe(viewLifecycleOwner, Observer {
            response ->
            list = response.results
            setBannerItem()
            mAdapter = NowPlayingAdapter(this,list)
            nowPlayRv.adapter = mAdapter
            nowPlayRv.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
        })
        banner.setOnClickListener{
            val bundle = Bundle()
            bundle.putSerializable("result",bannerItem)
            findNavController().navigate(R.id.action_home2_to_movieDetails,bundle)
        }
        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            response ->
            list = response.results
            popAdapter = NowPlayingAdapter(this,list)
            popMovRv.adapter = popAdapter
            popMovRv.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
        })

    }

    override fun onItemClicked(movie: Result) {
        val bundle = Bundle()
        bundle.putSerializable("result",movie)
        findNavController().navigate(R.id.action_home2_to_movieDetails,bundle)
    }


}