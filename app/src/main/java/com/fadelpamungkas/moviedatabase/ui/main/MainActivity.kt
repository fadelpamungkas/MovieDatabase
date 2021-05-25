package com.fadelpamungkas.moviedatabase.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadelpamungkas.core.data.Resource
import com.fadelpamungkas.core.ui.MovieAdapter
import com.fadelpamungkas.moviedatabase.R
import com.fadelpamungkas.moviedatabase.databinding.ActivityMainBinding
import com.fadelpamungkas.moviedatabase.ui.detail.DetailActivity
import com.fadelpamungkas.moviedatabase.ui.favorite.FavoriteActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }

        mainViewModel.movie.observe(this, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading ->
                        binding.progressbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressbar.visibility = View.GONE
                        movie.data?.let { movieAdapter.setMovies(it) }
                    }
                    is Resource.Error -> {
                        binding.progressbar.visibility = View.GONE
//                        binding.viewError.root.visibility = View.VISIBLE
//                        binding.viewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = movieAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite ->
                startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))

            else ->
                super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }
}