package com.fadelpamungkas.core.data.localsource

import androidx.room.Entity

@Entity(tableName = "movie_favorite", primaryKeys = ["id"])
data class MovieEntity(
    val id: Int,
    val photo: String,
    val title: String,
    var rating: Float? = null,
    var release: String? = null,
    var popularity: Float? = null,
    var description: String? = null,
    var isFavorite: Boolean = false
)