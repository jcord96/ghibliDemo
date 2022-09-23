package es.jco.ghiblidemo.data.server.mapper

import es.jco.ghiblidemo.data.server.dto.PeopleDTO
import es.jco.domain.Film as FilmDomain
import es.jco.domain.People as PeopleDomain
import es.jco.domain.Species as SpeciesDomain

fun PeopleDTO.toDomain() = PeopleDomain(
    this.id,
    this.name,
    this.gender,
    this.age,
    this.eyeColor,
    this.hairColor,
    this.films.map { FilmDomain(id = it) },
    SpeciesDomain(id = this.species)
)