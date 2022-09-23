package es.jco.ghiblidemo.data.database.dao

import androidx.room.*
import es.jco.ghiblidemo.data.database.entity.VehicleEntity
import es.jco.ghiblidemo.data.database.entity.VehicleParentEntity

@Dao
interface VehicleDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: VehicleEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<VehicleEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM VehicleEntity WHERE vehicleId = :vehicleId")
    suspend fun getVehicleById(vehicleId: String): VehicleParentEntity

    @Transaction
    @Query("SELECT * FROM VehicleEntity")
    suspend fun getVehicles(): List<VehicleParentEntity>
}