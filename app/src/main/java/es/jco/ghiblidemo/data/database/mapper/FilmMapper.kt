package es.jco.ghiblidemo.data.database.mapper

import es.jco.ghiblidemo.data.database.entity.FilmEntity
import es.jco.ghiblidemo.data.database.entity.FilmParentEntity
import java.util.*
import es.jco.domain.Film as FilmDomain


fun FilmEntity.toDomain() = FilmDomain(
    this.filmId,
    this.title,
    this.originalTitle,
    this.originalTitleRomanised,
    this.image,
    this.movieBanner,
    this.description,
    this.director,
    this.producer,
    this.releaseDate,
    this.runningTime,
    this.rtScore

)

fun FilmParentEntity.toDomain() = FilmDomain(
    this.film?.filmId,
    this.film?.title,
    this.film?.originalTitle,
    this.film?.originalTitleRomanised,
    this.film?.image,
    this.film?.movieBanner,
    this.film?.description,
    this.film?.director,
    this.film?.producer,
    this.film?.releaseDate,
    this.film?.runningTime,
    this.film?.rtScore,
    this.people?.map { it.toDomain() } ?: listOf(),
    this.species?.map { it.toDomain() } ?: listOf(),
    this.locations?.map { it.toDomain() } ?: listOf(),
    this.vehicles?.map { it.toDomain() } ?: listOf()
)

fun FilmDomain.toEntity() = FilmEntity(
        this.id ?: UUID.randomUUID().toString(),
        this.title,
        this.originalTitle,
        this.originalTitleRomanised,
        this.image,
        this.movieBanner,
        this.description,
        this.director,
        this.producer,
        this.releaseDate,
        this.runningTime,
        this.rtScore
)