package es.jco.data.repository

import es.jco.data.common.ResultData
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource

class VehicleRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun loadVehicles(): ResultData<Boolean> {
        return try {
            ResultData.success(remoteDataSource.getVehicles().getValue().let {
                if (!it.isNullOrEmpty()) {
                    localDataSource.insertVehicles(it)
                }

                localDataSource.countVehicles() > 0
            })
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}