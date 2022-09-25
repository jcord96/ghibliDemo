package es.jco.ghiblidemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.jco.data.repository.*
import es.jco.usecases.*

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

    @Provides
    fun getFilmByIdUseCaseProvider(filmRepository: FilmRepository) = GetFilmByIdUseCase(filmRepository)

    @Provides
    fun getFilmsUseCaseProvider(filmRepository: FilmRepository) = GetFilmsUseCase(filmRepository)

    @Provides
    fun saveFilmUseCaseProvider(filmRepository: FilmRepository) = SaveFilmUseCase(filmRepository)

    @Provides
    fun deleteFilmUseCaseProvider(filmRepository: FilmRepository) = DeleteFilmUseCase(filmRepository)
}