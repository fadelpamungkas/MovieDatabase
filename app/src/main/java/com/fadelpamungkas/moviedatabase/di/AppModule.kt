package com.fadelpamungkas.moviedatabase.di

import com.fadelpamungkas.core.domain.usecase.MovieInteractor
import com.fadelpamungkas.core.domain.usecase.MovieUseCase
import com.fadelpamungkas.moviedatabase.ui.detail.DetailViewModel
import com.fadelpamungkas.moviedatabase.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailViewModel(get()) }
    viewModel { MainViewModel(get()) }
}