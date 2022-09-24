package es.jco.data.repository

import es.jco.data.common.ResultData
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource

class FilmRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun loadFilms(): ResultData<Boolean> {
        return try {
            ResultData.success(remoteDataSource.getFilms().getValue().let {
                if (!it.isNullOrEmpty()) {
                    localDataSource.insertFilms(it)
                }

                localDataSource.countFilms() > 0
            })
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}