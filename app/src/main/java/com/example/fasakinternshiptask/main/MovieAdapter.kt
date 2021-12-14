package com.example.fasakinternshiptask.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.fasakinternshiptask.R
import com.example.fasakinternshiptask.databinding.ViewMovieBinding
import com.example.fasakinternshiptask.model.Movie

class MovieAdapter(private val list: List<Movie>, private val mainActivity: MovieSearchAcitivty): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ViewMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBindViewHolder(holder: ViewHolder) {
            with(holder) {
                with(list[position]) {
                    Glide.with(binding.moviePoster)
                        .load(this.Poster)
                        .placeholder(R.drawable.ic_baseline_search_24)
                        .error(R.drawable.ic_baseline_error_24)
                        .transform(CenterCrop())
                        .into(binding.moviePoster)
                    binding.movieTitle.text = this.Title
                    binding.movieYear.text = "Year: ${this.Year}"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}