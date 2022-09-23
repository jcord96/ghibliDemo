package es.jco.ghiblidemo.data.server.mapper

import es.jco.ghiblidemo.data.server.dto.SpeciesDTO
import es.jco.domain.Film as FilmDomain
import es.jco.domain.People as PeopleDomain
import es.jco.domain.Species as SpeciesDomain

fun SpeciesDTO.toDomain() = SpeciesDomain(
    this.id,
    this.name,
    this.classification,
    this.eyeColors,
    this.hairColors,
    this.people.map { PeopleDomain(id = it) },
    this.films.map { FilmDomain(id = it) }
)