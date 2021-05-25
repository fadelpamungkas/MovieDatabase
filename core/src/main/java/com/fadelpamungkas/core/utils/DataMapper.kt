package com.fadelpamungkas.core.utils

import com.fadelpamungkas.core.data.localsource.MovieEntity
import com.fadelpamungkas.core.data.remotesource.MovieResponse
import com.fadelpamungkas.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: MovieResponse): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            photo = input.photo,
            rating = input.rating,
            release = input.release,
            runtime = input.runtime,
            description = input.description,
            isFavorite = false
        )
    }
    fun mapResponsesToEntitiesList(input: List<MovieResponse>): List<MovieEntity> {
        val list = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                photo = it.photo,
                rating = it.rating,
                release = it.release,
                runtime = it.runtime,
                description = it.description,
                isFavorite = false
            )
            list.add(movie)
        }
        return list
    }

    fun mapEntitiesToModel(input: MovieEntity): Movie =
            Movie(
                id = input.id,
                title = input.title,
                photo = input.photo,
                rating = input.rating,
                release = input.release,
                runtime = input.runtime,
                description = input.description,
                isFavorite = input.isFavorite
            )


    fun mapEntitiesToModelList(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                photo = it.photo,
                rating = it.rating,
                release = it.release,
                runtime = it.runtime,
                description = it.description,
                isFavorite = it.isFavorite
            )
        }

    fun mapModelToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        photo = input.photo,
        rating = input.rating,
        release = input.release,
        runtime = input.runtime,
        description = input.description,
        isFavorite = input.isFavorite
    )
}