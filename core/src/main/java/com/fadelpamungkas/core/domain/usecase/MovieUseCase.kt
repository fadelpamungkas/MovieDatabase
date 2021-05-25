package com.fadelpamungkas.core.domain.usecase

import com.fadelpamungkas.core.data.Resource
import com.fadelpamungkas.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getDetailMovie(id: Int): Flow<Resource<Movie>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(Movie: Movie, state: Boolean)
}