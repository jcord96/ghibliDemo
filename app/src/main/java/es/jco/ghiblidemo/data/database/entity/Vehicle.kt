package es.jco.ghiblidemo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VehicleEntity (
    @PrimaryKey(autoGenerate = true) var vehicleId: Long?,
)