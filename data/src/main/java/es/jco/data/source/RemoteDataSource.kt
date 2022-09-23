package es.jco.data.source

import es.jco.data.common.Response
import es.jco.domain.*

interface RemoteDataSource {

    suspend fun getFilms(): Response<List<Film>>
    suspend fun getLocations(): Response<List<Location>>
    suspend fun getPeople(): Response<List<People>>
    suspend fun getSpecies(): Response<List<Species>>
    suspend fun getVehicles(): Response<List<Vehicle>>

}