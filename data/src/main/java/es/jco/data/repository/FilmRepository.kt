package es.jco.data.repository

import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource

class FilmRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
}