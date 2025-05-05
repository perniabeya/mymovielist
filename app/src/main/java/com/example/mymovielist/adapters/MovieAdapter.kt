package com.example.mymovielist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mymovielist.data.Movie
import com.example.mymovielist.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter (var items: List<Movie>, val onClick: (Int) -> Unit) : Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = items[position]
        holder.render(movie)
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }
}

class MovieViewHolder(val binding: ItemMovieBinding) : ViewHolder(binding.root) {

    fun render(movie: Movie) {
        binding.movieTextView.text = movie.Title
        binding.yearTextView.text = movie.Year
        Picasso.get().load(movie.Poster).into(binding.movieImageView);
    }
}