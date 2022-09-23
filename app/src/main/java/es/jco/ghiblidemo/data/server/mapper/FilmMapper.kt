package es.jco.ghiblidemo.data.server.mapper

import es.jco.ghiblidemo.data.server.dto.FilmDTO
import es.jco.domain.Film as FilmDomain
import es.jco.domain.Location as LocationDomain
import es.jco.domain.People as PeopleDomain
import es.jco.domain.Species as SpeciesDomain
import es.jco.domain.Vehicle as VehicleDomain

fun FilmDTO.toDomain() = FilmDomain(
    this.id,
    this.title,
    this.originalTitle,
    this.originalTitleRomanised,
    this.image,
    this.movieBanner,
    this.description,
    this.director,
    this.producer,
    this.releaseDate.toIntOrNull(),
    this.runningTime.toIntOrNull(),
    this.rtScore.toIntOrNull(),
    this.people.map { PeopleDomain(id = it) },
    this.species.map { SpeciesDomain(id = it) },
    this.locations.map { LocationDomain(id = it) },
    this.vehicles.map { VehicleDomain(id = it) }
)