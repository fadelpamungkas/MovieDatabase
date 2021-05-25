package com.fadelpamungkas.moviedatabase.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fadelpamungkas.core.domain.usecase.MovieUseCase

class MainViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}