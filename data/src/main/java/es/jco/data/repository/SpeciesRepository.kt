package es.jco.data.repository

import es.jco.data.common.ResultData
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource

class SpeciesRepository (
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun loadSpecies(): ResultData<Boolean> {
        return try {
            ResultData.success(remoteDataSource.getSpecies().getValue().let {
                if (!it.isNullOrEmpty()) {
                    localDataSource.insertSpecies(it)
                }

                localDataSource.countSpecies() > 0
            })
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}