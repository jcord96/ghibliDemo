package es.jco.ghiblidemo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity (
    @PrimaryKey(autoGenerate = true) var filmId: Long?,
)