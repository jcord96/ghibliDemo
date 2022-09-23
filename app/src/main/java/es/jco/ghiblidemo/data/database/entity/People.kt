package es.jco.ghiblidemo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PeopleEntity (
    @PrimaryKey(autoGenerate = true) var peopleId: Long?,
)