package es.jco.ghiblidemo.data.database.mapper

import es.jco.domain.Species as SpeciesDomain
import es.jco.ghiblidemo.data.database.entity.SpeciesEntity
import es.jco.ghiblidemo.data.database.entity.SpeciesParentEntity
import java.util.*

fun SpeciesEntity.toDomain() = SpeciesDomain(
    this.speciesId,
    this.name,
    this.classification,
    this.eyeColors,
    this.hairColors
)

fun SpeciesParentEntity.toDomain() = SpeciesDomain(
    this.species?.speciesId,
    this.species?.name,
    this.species?.classification,
    this.species?.eyeColors,
    this.species?.hairColors,
    this.peoples?.map { it.toDomain() } ?: listOf(),
    this.films?.map { it.toDomain() } ?: listOf()
)

fun SpeciesDomain.toEntity() = SpeciesEntity(
    this.id ?: UUID.randomUUID().toString(),
    this.name,
    this.classification,
    this.eyeColors,
    this.hairColors
)