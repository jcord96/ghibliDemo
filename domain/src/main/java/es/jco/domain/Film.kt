package es.jco.domain

data class Film (
    var id: String? = null,
    var title: String? = null,
    var originalTitle: String? = null,
    var originalTitleRomanised: String? = null,
    var image: String? = null,
    var movieBanner: String? = null,
    var description: String? = null,
    var director: String? = null,
    var producer: String? = null,
    var releaseDate: Int? = null,
    var runningTime: Int? = null,
    var rtScore: Int? = null,
    var people: List<People> = listOf(),
    var species: List<Species> = listOf(),
    var locations: List<Location> = listOf(),
    var vehicles:List<Vehicle> = listOf()
)