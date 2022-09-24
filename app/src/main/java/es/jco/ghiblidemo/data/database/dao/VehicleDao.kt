package es.jco.ghiblidemo.data.database.dao

import androidx.room.*
import es.jco.ghiblidemo.data.database.entity.VehicleEntity
import es.jco.ghiblidemo.data.database.entity.VehicleParentEntity

@Dao
interface VehicleDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vehicle: VehicleEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vehicles: List<VehicleEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM VehicleEntity WHERE vehicleId = :vehicleId")
    suspend fun getVehicleById(vehicleId: String): VehicleParentEntity

    @Transaction
    @Query("SELECT * FROM VehicleEntity")
    suspend fun getVehicles(): List<VehicleParentEntity>

    @Transaction
    @Query("SELECT COUNT(*) FROM VehicleEntity")
    suspend fun countVehicles(): Long
}