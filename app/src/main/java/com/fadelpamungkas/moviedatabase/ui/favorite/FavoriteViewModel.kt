package com.fadelpamungkas.moviedatabase.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fadelpamungkas.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val favMovie = movieUseCase.getFavoriteMovie().asLiveData()
}