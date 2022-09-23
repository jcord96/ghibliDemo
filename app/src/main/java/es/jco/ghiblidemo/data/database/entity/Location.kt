package es.jco.ghiblidemo.data.database.entity

import androidx.room.*

@Entity
data class LocationEntity (
    @PrimaryKey var locationId: String,
    var name: String?,
    var climate: String?,
    var terrain: String?,
    var surfaceWater: Int?
)

data class LocationParentEntity(
    @Embedded val location: LocationEntity?,
    @Relation(entity = PeopleEntity::class, parentColumn = "locationId", entityColumn = "peopleId", associateBy = Junction(LocationPeopleCrossRef::class)) val residents: List<PeopleEntity>?,
    @Relation(entity = FilmEntity::class, parentColumn = "locationId", entityColumn = "filmId", associateBy = Junction(FilmLocationCrossRef::class)) val films: List<FilmEntity>?
)