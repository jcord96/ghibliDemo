package es.jco.domain

data class Species (
    var id: String? = null,
    var name: String? = null,
    var classification: String? = null,
    var eyeColors: String? = null,
    var hairColors: String? = null,
    var people: List<People> = listOf(),
    var films: List<Film> = listOf()
)