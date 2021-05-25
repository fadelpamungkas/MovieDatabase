package com.fadelpamungkas.core.data

import com.dicoding.tourismapp.core.utils.AppExecutors
import com.fadelpamungkas.core.utils.DataMapper
import com.fadelpamungkas.core.data.localsource.LocalDataSource
import com.fadelpamungkas.core.data.remotesource.ApiResponse
import com.fadelpamungkas.core.data.remotesource.MovieResponse
import com.fadelpamungkas.core.data.remotesource.RemoteDataSource
import com.fadelpamungkas.core.domain.model.Movie
import com.fadelpamungkas.core.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : AppRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToModelList(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getPopularMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntitiesList(data)
                localDataSource.insertListMovie(movieList)
            }
        }.asFlow()

    override fun getDetailMovie(id: Int): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.findMovie(id).map {
                    DataMapper.mapEntitiesToModel(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getDetailMovie(id)

            override suspend fun saveCallResult(data: MovieResponse) {
                val movie = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movie)
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
           DataMapper.mapEntitiesToModelList(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapModelToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}

