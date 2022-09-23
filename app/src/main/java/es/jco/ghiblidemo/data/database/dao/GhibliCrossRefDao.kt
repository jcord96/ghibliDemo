package es.jco.ghiblidemo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import es.jco.ghiblidemo.data.database.entity.*

@Dao
interface GhibliCrossRefDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(address: FilmLocationCrossRef): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(address: FilmPeopleCrossRef): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(address: FilmSpeciesCrossRef): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(address: FilmVehicleCrossRef): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(address: LocationPeopleCrossRef): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllFilmLocationCrossRef(filmLocationCrossRefList: List<FilmLocationCrossRef>): List<Long>
    
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllFilmPeopleCrossRef(filmPeopleCrossRefList: List<FilmPeopleCrossRef>): List<Long>
    
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllFilmSpeciesCrossRef(filmSpeciesCrossRefList: List<FilmSpeciesCrossRef>): List<Long>
    
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllFilmVehicleCrossRef(filmVehicleCrossRefList: List<FilmVehicleCrossRef>): List<Long>
    
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllLocationPeopleCrossRef(locationPeopleCrossRefList: List<LocationPeopleCrossRef>): List<Long>

}