package es.jco.ghiblidemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.jco.ghiblidemo.data.database.dao.*
import es.jco.ghiblidemo.data.database.entity.*

/**
 * App room database
 *
 * @constructor Create empty App room database
 */
@Database(
    entities = [
        FilmEntity::class,
        LocationEntity::class,
        PeopleEntity::class,
        SpeciesEntity::class,
        VehicleEntity::class,
    ],
    version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao
    abstract fun locationDao(): LocationDao
    abstract fun peopleDao(): PeopleDao
    abstract fun specieDao(): SpeciesDao
    abstract fun vehicleDao(): VehicleDao
}