package es.jco.ghiblidemo.data.server

import es.jco.data.source.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Retrofit as remote data source
 *
 * @property apiService
 * @constructor Create empty Server data source
 */
class ServerDataSource @Inject constructor(val apiService: APIService) : RemoteDataSource {

    companion object {
        private val requestDispatcher: CoroutineDispatcher = Dispatchers.IO
    }

}