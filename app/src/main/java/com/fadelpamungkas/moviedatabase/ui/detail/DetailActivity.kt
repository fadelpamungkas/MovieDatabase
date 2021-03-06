package com.fadelpamungkas.moviedatabase.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fadelpamungkas.core.data.Resource
import com.fadelpamungkas.core.domain.model.Movie
import com.fadelpamungkas.moviedatabase.R
import com.fadelpamungkas.moviedatabase.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel: DetailViewModel by viewModel()

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.detailmovie)

        var movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        if (movie != null) {
            detailViewModel.getDetailMovie(movie.id).observe(this, { response ->
                if (response != null) {
                    when (response) {
                        is Resource.Loading ->
                            binding.progressbar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressbar.visibility = View.GONE
                            Log.d("getDetailMovie", response.data.toString())
                            movie = response.data
                            movie?.let {
                                bindView(it)
                            }
                        }
                        is Resource.Error -> {
                            binding.progressbar.visibility = View.GONE
                        }
                    }
                }
            })

        }

    }

    private fun bindView(movie: Movie) {
        var favorite = movie.isFavorite
        favoriteState(favorite)

        with(binding) {
            progressbar.visibility = View.GONE

            titleDetail.text = movie.title
            rating.text = getString(R.string.rating)
            ratingDetail.text = movie.rating.toString()
            release.text = getString(R.string.release_date)
            releaseDetail.text = movie.release
            popularity.text = getString(R.string.popularity)
            popularityDetail.text = movie.popularity.toString()
            descDetail.text = movie.description
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w185" + movie.photo)
                .into(imageDetail)
        }

        binding.fabFavorite.setOnClickListener { view ->
            favorite = !favorite
            if (favorite) {
                Snackbar.make(view, getString(R.string.add_to_bookmark), Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(view, getString(R.string.remove_from_bookmark), Snackbar.LENGTH_SHORT).show()
            }
            detailViewModel.setFavorite(movie, favorite)
            favoriteState(favorite)
        }

    }

    private fun favoriteState(state: Boolean) {
        if (state) {
            binding.fabFavorite.setImageResource(R.drawable.ic_bookmark_24)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_bookmark_border_24)
        }
    }
}