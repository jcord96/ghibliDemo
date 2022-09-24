package es.jco.ghiblidemo.data.server

import es.jco.ghiblidemo.data.server.dto.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIService {

    @Headers(ServerConstants.HEADER_ACCEPT_TYPE, ServerConstants.HEADER_CONTENT_TYPE)
    @GET("${ServerConstants.ENDPOINT_FILMS}")
    suspend fun getFilms(): Response<List<FilmDTO>>

    @Headers(ServerConstants.HEADER_ACCEPT_TYPE, ServerConstants.HEADER_CONTENT_TYPE)
    @GET("${ServerConstants.ENDPOINT_LOCATIONS}")
    suspend fun getLocations(): Response<List<LocationDTO>>

    @Headers(ServerConstants.HEADER_ACCEPT_TYPE, ServerConstants.HEADER_CONTENT_TYPE)
    @GET("${ServerConstants.ENDPOINT_PEOPLE}")
    suspend fun getPeople(): Response<List<PeopleDTO>>

    @Headers(ServerConstants.HEADER_ACCEPT_TYPE, ServerConstants.HEADER_CONTENT_TYPE)
    @GET("${ServerConstants.ENDPOINT_SPECIES}")
    suspend fun getSpecies(): Response<List<SpeciesDTO>>

    @Headers(ServerConstants.HEADER_ACCEPT_TYPE, ServerConstants.HEADER_CONTENT_TYPE)
    @GET("${ServerConstants.ENDPOINT_VEHICLES}")
    suspend fun getVehicles(): Response<List<VehicleDTO>>
}