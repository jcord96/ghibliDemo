package es.jco.domain

data class Location (
    var id: String? = null,
    var name: String? = null,
    var climate: String? = null,
    var terrain: String? = null,
    var surfaceWater: Int? = null,
    var residents: List<People> = listOf(),
    var films: List<Film> = listOf()
)