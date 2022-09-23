package es.jco.ghiblidemo.data.database.mapper

import es.jco.domain.Vehicle as VehicleDomain
import es.jco.ghiblidemo.data.database.entity.VehicleEntity
import es.jco.ghiblidemo.data.database.entity.VehicleParentEntity
import java.util.*

fun VehicleEntity.toDomain() = VehicleDomain(
    this.vehicleId,
    this.name,
    this.description,
    this.vehicleClass,
    this.length,
)

fun VehicleParentEntity.toDomain() = VehicleDomain(
    this.vehicle?.vehicleId,
    this.vehicle?.name,
    this.vehicle?.description,
    this.vehicle?.vehicleClass,
    this.vehicle?.length,
    this.pilot?.toDomain(),
    this.films?.map { it.toDomain() } ?: listOf()
)

fun  VehicleDomain.toEntity() = VehicleEntity(
    this.id ?: UUID.randomUUID().toString(),
    this.name,
    this.description,
    this.vehicleClass,
    this.length,
    this.pilot?.id
)