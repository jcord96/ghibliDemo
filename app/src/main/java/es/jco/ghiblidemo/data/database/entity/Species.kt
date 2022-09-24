package es.jco.ghiblidemo.data.database.entity

import androidx.room.*

@Entity
data class SpeciesEntity (
    @PrimaryKey var speciesId: String,
    var name: String?,
    var classification: String?,
    var eyeColors: String?,
    var hairColors: String?,
)

data class SpeciesParentEntity(
    @Embedded val species: SpeciesEntity?,
    @Relation(entity = PeopleEntity::class, parentColumn = "speciesId", entityColumn = "speciesId") val peoples: List<PeopleEntity>?,
    @Relation(parentColumn = "speciesId", entityColumn = "filmId", associateBy = Junction(FilmSpeciesCrossRef::class)) val films: List<FilmEntity>?
)