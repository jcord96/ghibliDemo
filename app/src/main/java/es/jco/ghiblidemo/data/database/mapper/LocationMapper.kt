package es.jco.ghiblidemo.data.database.mapper

import es.jco.domain.Location as LocationDomain
import es.jco.ghiblidemo.data.database.entity.LocationEntity
import es.jco.ghiblidemo.data.database.entity.LocationParentEntity

fun LocationEntity.toDomain() = LocationDomain(
    this.locationId,
    this.name,
    this.climate,
    this.terrain,
    this.surfaceWater
)

fun LocationParentEntity.toDomain() = LocationDomain(
    this.location?.locationId,
    this.location?.name,
    this.location?.climate,
    this.location?.terrain,
    this.location?.surfaceWater,
    this.residents?.map { it.toDomain() } ?: listOf(),
    this.films?.map { it.toDomain() } ?: listOf()
)

fun LocationDomain.toEntity() = LocationEntity(
    this.id,
    this.name,
    this.climate,
    this.terrain,
    this.surfaceWater
)