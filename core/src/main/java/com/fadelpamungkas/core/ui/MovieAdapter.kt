package com.fadelpamungkas.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fadelpamungkas.core.databinding.CardviewItemBinding
import com.fadelpamungkas.core.domain.model.Movie
import kotlin.collections.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>() {

    private var movies = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setMovies(movieList: List<Movie>) {
        movies.clear()
        movies.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val binding = CardviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class CardViewViewHolder(private val binding: CardviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie){
            with(binding){
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185" + movie.photo)
                    .into(imageMain)
                titleMain.text = movie.title

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition])
            }
        }
    }

}