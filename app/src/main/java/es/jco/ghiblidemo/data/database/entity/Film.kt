package es.jco.ghiblidemo.data.database.entity

import androidx.room.*

@Entity
data class FilmEntity (
    @PrimaryKey var filmId: String,
    var title: String?,
    var originalTitle: String?,
    var originalTitleRomanised: String?,
    var image: String?,
    var movieBanner: String?,
    var description: String?,
    var director: String?,
    var producer: String?,
    var releaseDate: Int?,
    var runningTime: Int?,
    var rtScore: Int?,
)

data class FilmParentEntity(
    @Embedded val film: FilmEntity?,
    @Relation(parentColumn = "filmId", entityColumn = "peopleId", associateBy = Junction(FilmPeopleCrossRef::class)) val people: List<PeopleEntity>?,
    @Relation(parentColumn = "filmId", entityColumn = "speciesId", associateBy = Junction(FilmSpeciesCrossRef::class)) val species: List<SpeciesEntity>?,
    @Relation(parentColumn = "filmId", entityColumn = "locationId", associateBy = Junction(FilmLocationCrossRef::class)) val locations: List<LocationEntity>?,
    @Relation(parentColumn = "filmId", entityColumn = "vehicleId", associateBy = Junction(FilmVehicleCrossRef::class)) val vehicles: List<VehicleEntity>?
)