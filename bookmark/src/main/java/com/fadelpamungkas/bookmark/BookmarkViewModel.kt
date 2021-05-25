package com.fadelpamungkas.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fadelpamungkas.core.domain.usecase.MovieUseCase

class BookmarkViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val favMovie = movieUseCase.getFavoriteMovie().asLiveData()
}