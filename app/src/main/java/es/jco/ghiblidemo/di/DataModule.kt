package es.jco.ghiblidemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.jco.data.repository.*
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource

/**
 * Data module
 * This class declares all providers to inject the repositories
 *
 * @constructor Create empty Data module
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    
    @Provides
    fun filmRepositoryProvider(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): FilmRepository = FilmRepository(localDataSource, remoteDataSource)

    @Provides
    fun locationRepositoryProvider(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): LocationRepository = LocationRepository(localDataSource, remoteDataSource)

    @Provides
    fun peopleRepositoryProvider(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): PeopleRepository = PeopleRepository(localDataSource, remoteDataSource)

    @Provides
    fun speciesRepositoryProvider(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): SpeciesRepository = SpeciesRepository(localDataSource, remoteDataSource)

    @Provides
    fun vehicleRepositoryProvider(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): VehicleRepository = VehicleRepository(localDataSource, remoteDataSource)
}