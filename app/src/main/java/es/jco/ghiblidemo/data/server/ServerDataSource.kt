package es.jco.ghiblidemo.data.server

import es.jco.data.common.Response
import es.jco.data.source.RemoteDataSource
import es.jco.domain.*
import es.jco.ghiblidemo.data.server.mapper.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Retrofit as remote data source
 *
 * @property apiService
 * @constructor Create empty Server data source
 */
class ServerDataSource @Inject constructor(
    private val apiService: APIService,
    private val requestDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteDataSource {
    
    /**
     * Request to get  films
     *
     * @return
     */
    override suspend fun getFilms(): Response<List<Film>> {
        return withContext(requestDispatcher) {
            try {
                val response = apiService.getFilms()
                Response(value = response.body()?.map { filmDTO -> filmDTO.toDomain() } ?: emptyList())
            } catch (e: Exception) {
                Response(error = e.cause)
            }
        }
    }

    /**
     * Request to get locations
     *
     * @return
     */
    override suspend fun getLocations(): Response<List<Location>> {
        return withContext(requestDispatcher) {
            try {
                val response = apiService.getLocations()
                Response(value = response.body()?.map { locationDTO -> locationDTO.toDomain() } ?: emptyList())
            } catch (e: Exception) {
                Response(error = e.cause)
            }
        }
    }

    /**
     * Request to get people
     *
     * @return
     */
    override suspend fun getPeople(): Response<List<People>> {
        return withContext(requestDispatcher) {
            try {
                val response = apiService.getPeople()
                Response(value = response.body()?.map { peopleDTO -> peopleDTO.toDomain() } ?: emptyList())
            } catch (e: Exception) {
                Response(error = e.cause)
            }
        }
    }

    /**
     * Request to get species
     *
     * @return
     */
    override suspend fun getSpecies(): Response<List<Species>> {
        return withContext(requestDispatcher) {
            try {
                val response = apiService.getSpecies()
                Response(value = response.body()?.map { speciesDTO -> speciesDTO.toDomain() } ?: emptyList())
            } catch (e: Exception) {
                Response(error = e.cause)
            }
        }
    }

    /**
     * Request to get vehicles
     *
     * @return
     */
    override suspend fun getVehicles(): Response<List<Vehicle>> {
        return withContext(requestDispatcher) {
            try {
                val response = apiService.getVehicles()
                Response(value = response.body()?.map { vehicleDTO -> vehicleDTO.toDomain() } ?: emptyList())
            } catch (e: Exception) {
                Response(error = e.cause)
            }
        }
    }

}