package com.fadelpamungkas.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val photo: String,
    val title: String,
    var rating: Float? = null,
    var release: String? = null,
    var popularity: Float? = null,
    var description: String? = null,
    var isFavorite: Boolean = false
) : Parcelable