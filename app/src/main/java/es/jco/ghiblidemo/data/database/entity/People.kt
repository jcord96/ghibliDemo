package es.jco.ghiblidemo.data.database.entity

import androidx.room.*

@Entity
data class PeopleEntity (
    @PrimaryKey(autoGenerate = true) var peopleId: String?,
    var name: String?,
    var gender: String?,
    var age: String?,
    var eyeColor: String?,
    var hairColor: String?,
    var speciesId: String?
)

data class PeopleParentEntity(
    @Embedded val people: PeopleEntity?,
    @Relation(entity = PeopleEntity::class, parentColumn = "speciesId", entityColumn = "speciesId") val species: SpeciesEntity?,
    @Relation(entity = FilmEntity::class, parentColumn = "peopleId", entityColumn = "filmId", associateBy = Junction(FilmPeopleCrossRef::class)) val films: List<FilmEntity>?
)