package com.fadelpamungkas.core.domain.usecase

import com.fadelpamungkas.core.domain.model.Movie
import com.fadelpamungkas.core.domain.repository.AppRepository

class MovieInteractor(private val appRepository: AppRepository): MovieUseCase {

    override fun getAllMovie() = appRepository.getAllMovie()

    override fun getDetailMovie(id: Int)= appRepository.getDetailMovie(id)

    override fun getFavoriteMovie() = appRepository.getFavoriteMovie()

    override fun setFavoriteMovie(Movie: Movie, state: Boolean) = appRepository.setFavoriteMovie(Movie, state)
}