package com.fadelpamungkas.bookmark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadelpamungkas.bookmark.databinding.ActivityBookmarkBinding
import com.fadelpamungkas.bookmark.di.bookmarkModule
import com.fadelpamungkas.core.ui.MovieAdapter
import com.fadelpamungkas.moviedatabase.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBookmarkBinding

    private val bookmarkViewModel: BookmarkViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookmarkBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.mybookmark)

        loadKoinModules(bookmarkModule)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }

        bookmarkViewModel.favMovie.observe(this, { movie ->
            movieAdapter.setMovies(movie)
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = movieAdapter

    }

}