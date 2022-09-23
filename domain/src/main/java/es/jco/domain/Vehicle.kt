package es.jco.domain

data class Vehicle (
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var vehicleClass: String? = null,
    var length: Int? = null,
    var pilot: People? = People(),
    var films: List<Film> = listOf()
)