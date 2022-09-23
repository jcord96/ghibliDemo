package es.jco.ghiblidemo.data.database.dao

import androidx.room.*
import es.jco.ghiblidemo.data.database.entity.SpeciesEntity
import es.jco.ghiblidemo.data.database.entity.SpeciesParentEntity

@Dao
interface SpeciesDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: SpeciesEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<SpeciesEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM SpeciesEntity WHERE speciesId = :speciesId")
    suspend fun getSpeciesById(speciesId: String): SpeciesParentEntity

    @Transaction
    @Query("SELECT * FROM SpeciesEntity")
    suspend fun getSpecies(): List<SpeciesParentEntity>
}