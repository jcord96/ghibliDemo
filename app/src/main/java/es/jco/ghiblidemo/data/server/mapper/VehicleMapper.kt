package es.jco.ghiblidemo.data.server.mapper

import es.jco.ghiblidemo.data.commons.searchId
import es.jco.ghiblidemo.data.server.dto.VehicleDTO
import es.jco.domain.Film as FilmDomain
import es.jco.domain.People as PeopleDomain
import es.jco.domain.Vehicle as VehicleDomain

fun VehicleDTO.toDomain() = VehicleDomain(
    this.id,
    this.name,
    this.description,
    this.vehicleClass,
    this.length.toIntOrNull(),
    PeopleDomain(id = this.pilot.searchId()),
    this.films.filter { !it.searchId().isNullOrEmpty() }.map { FilmDomain(id = it.searchId()) }
)
