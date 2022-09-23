package es.jco.ghiblidemo.data.database.dao

import androidx.room.*
import es.jco.ghiblidemo.data.database.entity.PeopleEntity
import es.jco.ghiblidemo.data.database.entity.PeopleParentEntity

@Dao
interface PeopleDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: PeopleEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PeopleEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM PeopleEntity WHERE peopleId = :peopleId")
    suspend fun getPeopleById(peopleId: String): PeopleParentEntity

    @Transaction
    @Query("SELECT * FROM PeopleEntity")
    suspend fun getPeople(): List<PeopleParentEntity>
}