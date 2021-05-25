package com.fadelpamungkas.moviedatabase.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadelpamungkas.core.ui.MovieAdapter
import com.fadelpamungkas.moviedatabase.databinding.ActivityFavoriteBinding
import com.fadelpamungkas.moviedatabase.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favMovie.observe(this, { movie ->
            movieAdapter.setMovies(movie)
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = movieAdapter

    }

}