package es.jco.ghiblidemo.data.server.mapper

import es.jco.ghiblidemo.data.server.dto.LocationDTO
import es.jco.domain.Film as FilmDomain
import es.jco.domain.Location as LocationDomain
import es.jco.domain.People as PeopleDomain

fun LocationDTO.toDomain() = LocationDomain(
    this.id,
    this.name,
    this.climate,
    this.terrain,
    this.surfaceWater.toIntOrNull(),
    this.residents.map { PeopleDomain(id = it) },
    this.films.map { FilmDomain(id = it) }
)