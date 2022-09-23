package es.jco.ghiblidemo.data.database.dao

import androidx.room.*
import es.jco.ghiblidemo.data.database.entity.LocationEntity
import es.jco.ghiblidemo.data.database.entity.LocationParentEntity

@Dao
interface LocationDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: LocationEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<LocationEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM LocationEntity WHERE locationId = :locationId")
    suspend fun getLocationById(locationId: String): LocationParentEntity

    @Transaction
    @Query("SELECT * FROM LocationEntity")
    suspend fun getLocations(): List<LocationParentEntity>
}