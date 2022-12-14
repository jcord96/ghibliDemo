package es.jco.data.source

import es.jco.domain.*
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertFilm(film: Film)
    suspend fun insertFilms(films: List<Film>)
    suspend fun getFilmById(filmId: String): Film
    fun getFilmsUpdatable(): Flow<List<Film>>
    suspend fun deleteFilm(filmId: String): Boolean
    suspend fun countFilms(): Long

    suspend fun insertLocation(location: Location)
    suspend fun insertLocations(locations: List<Location>)
    suspend fun countLocations(): Long

    suspend fun insertPeople(people: People)
    suspend fun insertPeople(people: List<People>)
    suspend fun countPeople(): Long

    suspend fun insertSpecies(species: Species)
    suspend fun insertSpecies(species: List<Species>)
    suspend fun countSpecies(): Long

    suspend fun insertVehicle(vehicle: Vehicle)
    suspend fun insertVehicles(vehicles: List<Vehicle>)
    suspend fun countVehicles(): Long
}