package es.jco.ghiblidemo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpeciesEntity (
    @PrimaryKey(autoGenerate = true) var specieId: Long?,
)