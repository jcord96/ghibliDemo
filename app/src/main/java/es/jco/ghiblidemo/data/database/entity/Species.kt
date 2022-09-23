package es.jco.ghiblidemo.data.database.entity

import androidx.room.*

@Entity
data class SpeciesEntity (
    @PrimaryKey(autoGenerate = true) var speciesId: String?,
    var name: String?,
    var classification: String?,
    var eyeColors: String?,
    var hairColors: String?,
)

data class SpeciesParentEntity(
    @Embedded val species: SpeciesEntity?,
    @Relation(entity = PeopleEntity::class, parentColumn = "speciesId", entityColumn = "speciesId") val peoples: List<PeopleEntity>?,
    @Relation(entity = FilmEntity::class, parentColumn = "speciesId", entityColumn = "filmId", associateBy = Junction(FilmPeopleCrossRef::class)) val films: List<FilmEntity>?
)