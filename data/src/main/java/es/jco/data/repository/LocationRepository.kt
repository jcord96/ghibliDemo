package es.jco.data.repository

import es.jco.data.common.ResultData
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource

class LocationRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun loadLocations(): ResultData<Boolean> {
        return try {
            ResultData.success(remoteDataSource.getLocations().getValue().let {
                if (!it.isNullOrEmpty()) {
                    localDataSource.insertLocations(it)
                }

                localDataSource.countLocations() > 0
            })
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}