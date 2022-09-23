package es.jco.ghiblidemo.data.database.entity

import androidx.room.*

@Entity
data class VehicleEntity (
    @PrimaryKey(autoGenerate = true) var vehicleId: String?,
    var name: String?,
    var description: String?,
    var vehicleClass: String?,
    var length: Int?,
    var pilotId: String?
)

data class VehicleParentEntity(
    @Embedded val vehicle: VehicleEntity?,
    @Relation(entity = PeopleEntity::class, parentColumn = "pilotId", entityColumn = "peopleId") val pilot: PeopleEntity?,
    @Relation(entity = FilmEntity::class, parentColumn = "vehicleId", entityColumn = "filmId", associateBy = Junction(FilmVehicleCrossRef::class)) val films: List<FilmEntity>?
)