package es.jco.ghiblidemo.data.database.dao

import androidx.room.*
import es.jco.ghiblidemo.data.database.entity.LocationEntity
import es.jco.ghiblidemo.data.database.entity.LocationParentEntity

@Dao
interface LocationDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: LocationEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<LocationEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM LocationEntity WHERE locationId = :locationId")
    suspend fun getLocationById(locationId: String): LocationParentEntity

    @Transaction
    @Query("SELECT * FROM LocationEntity")
    suspend fun getLocations(): List<LocationParentEntity>

    @Transaction
    @Query("SELECT COUNT(*) FROM LocationEntity")
    suspend fun countLocations(): Long
}