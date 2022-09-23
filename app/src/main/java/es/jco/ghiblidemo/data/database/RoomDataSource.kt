package es.jco.ghiblidemo.data.database

import es.jco.data.source.LocalDataSource
import javax.inject.Inject

/**
 * Room as local data source
 *
 * @property appRoomDatabase
 * @constructor Create empty Room data source
 */
class RoomDataSource @Inject constructor(private val appRoomDatabase: AppRoomDatabase) : LocalDataSource