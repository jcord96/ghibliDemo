package es.jco.ghiblidemo.data.database.dao

import androidx.room.*
import es.jco.ghiblidemo.data.database.entity.FilmEntity
import es.jco.ghiblidemo.data.database.entity.FilmParentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: FilmEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(films: List<FilmEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM FilmEntity WHERE filmId = :filmId")
    suspend fun getFilmById(filmId: String): FilmParentEntity

    @Transaction
    @Query("SELECT * FROM FilmEntity")
    suspend fun getFilms(): List<FilmParentEntity>

    @Transaction
    @Query("SELECT * FROM FilmEntity")
    fun getFilmsUpdatable(): Flow<List<FilmParentEntity>>

    @Transaction
    @Query("SELECT COUNT(*) FROM FilmEntity")
    suspend fun countFilms(): Long

    @Transaction
    @Query("DELETE FROM FilmEntity WHERE filmId = :filmId")
    fun deleteFilm(filmId: String)
}