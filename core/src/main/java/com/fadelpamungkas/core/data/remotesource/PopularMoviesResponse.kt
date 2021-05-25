package com.fadelpamungkas.core.data.remotesource

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("results")
    val result: ArrayList<MovieResponse>
)
