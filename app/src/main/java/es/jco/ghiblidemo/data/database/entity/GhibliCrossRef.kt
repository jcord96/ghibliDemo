package es.jco.ghiblidemo.data.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["filmId", "locationId"])
data class FilmLocationCrossRef(
    val filmId: String,
    val locationId: String
)

@Entity(primaryKeys = ["filmId", "peopleId"])
data class FilmPeopleCrossRef(
    val filmId: String,
    val peopleId: String
)

@Entity(primaryKeys = ["filmId", "speciesId"])
data class FilmSpeciesCrossRef(
    val filmId: String,
    val speciesId: String
)

@Entity(primaryKeys = ["filmId", "vehicleId"])
data class FilmVehicleCrossRef(
    val filmId: String,
    val vehicleId: String
)

@Entity(primaryKeys = ["locationId", "peopleId"])
data class LocationPeopleCrossRef(
    val locationId: String,
    val peopleId: String
)