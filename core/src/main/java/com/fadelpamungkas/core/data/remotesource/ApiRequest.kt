package com.fadelpamungkas.core.data.remotesource

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): PopularMoviesResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): MovieResponse

}