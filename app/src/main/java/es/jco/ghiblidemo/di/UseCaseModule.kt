package es.jco.ghiblidemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.jco.data.repository.*
import es.jco.usecases.LoadDataUseCase

/**
 * Use case module
 * This class declares all providers to inject the use cases
 *
 * @constructor Create empty Use case module
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun loadDataUseCaseProvider(
        filmRepository: FilmRepository,
        locationRepository: LocationRepository,
        peopleRepository: PeopleRepository,
        speciesRepository: SpeciesRepository,
        vehicleRepository: VehicleRepository
    ) = LoadDataUseCase(filmRepository, locationRepository, peopleRepository, speciesRepository, vehicleRepository)
}