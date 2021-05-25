package com.fadelpamungkas.core.data.localsource

import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val appDAO: AppDAO) {

    fun getAllMovie(): Flow<List<MovieEntity>> = appDAO.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = appDAO.getFavoriteMovie()

    fun findMovie(id: Int): Flow<MovieEntity> = appDAO.findMovie(id)

    suspend fun insertMovie(movie: MovieEntity) = appDAO.insert(movie)

    suspend fun insertListMovie(movie: List<MovieEntity>) = appDAO.insertList(movie)

    fun setFavoriteMovie(Movie: MovieEntity, newState: Boolean) {
        Movie.isFavorite = newState
        appDAO.updateFavoriteMovie(Movie)
    }
}