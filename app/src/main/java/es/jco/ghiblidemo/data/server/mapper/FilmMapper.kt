package es.jco.ghiblidemo.data.server.mapper

import es.jco.ghiblidemo.data.commons.searchId
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
    this.people.filter { !it.searchId().isNullOrEmpty() }.map { PeopleDomain(id = it.searchId()) },
    this.species.filter { !it.searchId().isNullOrEmpty() }.map { SpeciesDomain(id = it.searchId()) },
    this.locations.filter { !it.searchId().isNullOrEmpty() }.map { LocationDomain(id = it.searchId()) },
    this.vehicles.filter { !it.searchId().isNullOrEmpty() }.map { VehicleDomain(id = it.searchId()) }
)