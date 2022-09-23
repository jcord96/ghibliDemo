package es.jco.domain

data class People (
    var id: String? = null,
    var name: String? = null,
    var gender: String? = null,
    var age: String? = null,
    var eyeColor: String? = null,
    var hairColor: String? = null,
    var films: List<Film> = listOf(),
    var species: Species? = Species()
)