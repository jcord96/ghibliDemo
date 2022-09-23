package es.jco.ghiblidemo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationEntity (
    @PrimaryKey(autoGenerate = true) var locationId: Long?,
)