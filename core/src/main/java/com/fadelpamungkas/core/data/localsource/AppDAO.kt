package com.fadelpamungkas.core.data.localsource

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(movie: List<MovieEntity>)

    @Query("delete from movie_favorite where id = :id")
    suspend fun deleteMovie(id: Int)

    @Query("SELECT * FROM movie_favorite")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_favorite where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Query("select * from movie_favorite where id = :id")
    fun findMovie(id: Int) : Flow<MovieEntity>

}