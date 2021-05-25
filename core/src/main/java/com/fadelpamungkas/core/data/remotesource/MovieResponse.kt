package com.fadelpamungkas.core.data.remotesource

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val photo: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    var rating: Float? = null,
    @SerializedName("release_date")
    var release: String? = null,
    @SerializedName("runtime")
    var runtime: Int? = null,
    @SerializedName("overview")
    var description: String? = null
)