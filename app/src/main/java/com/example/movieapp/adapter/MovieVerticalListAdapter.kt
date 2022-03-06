package com.example.movieapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.Result
import kotlinx.android.synthetic.main.movie_item.view.title
import kotlinx.android.synthetic.main.vertical_movie_item.view.*

class MovieVerticalListAdapter(val listener:MovieVerticalClick, var list: List<Result>?):RecyclerView.Adapter<MovieVerticalListAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieVerticalListAdapter.ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vertical_movie_item,parent,false)
        val viewHolder = ItemViewHolder(view)
        Log.d("Inside CreateViewHolder","inside")
        view.rootView.setOnClickListener{
            (list?.get(viewHolder.adapterPosition) ?: null)?.let { it1 -> listener.onItemClicked(it1) }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MovieVerticalListAdapter.ItemViewHolder, position: Int) {
        val movie = list?.get(holder.adapterPosition)
        holder.itemView.apply {
            Glide.with(this).load("https://image.tmdb.org/t/p/original/${movie?.posterPath}").into(movImg)
            title.text = movie?.title
            rating.text = movie?.voteAverage.toString()
            summary.text = movie?.overview
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?:0
    }
    class ItemViewHolder(view: View):RecyclerView.ViewHolder(view){

    }


}

interface MovieVerticalClick {
fun onItemClicked(movie:Result?)
}

