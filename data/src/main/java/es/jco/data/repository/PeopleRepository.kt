package es.jco.data.repository

import es.jco.data.common.ResultData
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource

class PeopleRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun loadPeople(): ResultData<Boolean> {
        return try {
            ResultData.success(remoteDataSource.getPeople().getValue().let {
                if (!it.isNullOrEmpty()) {
                    localDataSource.insertPeople(it)
                }

                localDataSource.countPeople() > 0
            })
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}