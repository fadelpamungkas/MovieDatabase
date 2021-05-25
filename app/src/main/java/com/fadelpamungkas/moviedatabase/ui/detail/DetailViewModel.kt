package com.fadelpamungkas.moviedatabase.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fadelpamungkas.core.domain.model.Movie
import com.fadelpamungkas.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavorite(movie: Movie, state: Boolean) = movieUseCase.setFavoriteMovie(movie, state)

    fun getDetailMovie(id: Int) = movieUseCase.getDetailMovie(id).asLiveData()

}