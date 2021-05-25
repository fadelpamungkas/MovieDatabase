package com.fadelpamungkas.core.domain.repository

import com.fadelpamungkas.core.data.Resource
import com.fadelpamungkas.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getDetailMovie(id: Int): Flow<Resource<Movie>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}