package com.fadelpamungkas.core.data.remotesource

import android.util.Log
import com.fadelpamungkas.core.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiRequest: ApiRequest) {

    suspend fun getPopularMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiRequest.getPopularMovies(BuildConfig.API_KEY)
                val dataArray = response.result
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiRequest.getDetailMovie(id, BuildConfig.API_KEY)
                if (!response.equals(null)){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

