package es.jco.ghiblidemo.data.database.mapper

import es.jco.domain.People as PeopleDomain
import es.jco.ghiblidemo.data.database.entity.PeopleEntity
import es.jco.ghiblidemo.data.database.entity.PeopleParentEntity

fun PeopleEntity.toDomain() = PeopleDomain(
    this.peopleId,
    this.name,
    this.gender,
    this.age,
    this.eyeColor,
    this.hairColor
)

fun PeopleParentEntity.toDomain() = PeopleDomain(
    this.people?.peopleId,
    this.people?.name,
    this.people?.gender,
    this.people?.age,
    this.people?.eyeColor,
    this.people?.hairColor,
    this.films?.map { it.toDomain() } ?: listOf(),
    this.species?.toDomain()
)

fun PeopleDomain.toEntity() = PeopleEntity(
        this.id,
        this.name,
        this.gender,
        this.age,
        this.eyeColor,
        this.hairColor,
        this.species?.id
)