package es.jco.data.repository

import es.jco.data.common.ResultData
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource
import es.jco.domain.Film
import kotlinx.coroutines.flow.Flow

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

    suspend fun getFilm(filmId: String): ResultData<Film> {
        return try {
            ResultData.success(localDataSource.getFilmById(filmId))
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }

    fun getFilms(): ResultData<Flow<List<Film>>> {
        return try {
            ResultData.success(localDataSource.getFilmsUpdatable())
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }

    suspend fun saveFilm(film: Film): ResultData<Boolean> {
        return try {
            localDataSource.insertFilm(film)
            ResultData.success(true)
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }

    suspend fun deleteFilm(filmId: String): ResultData<Boolean> {
        return try {
            if (localDataSource.deleteFilm(filmId)) {
                ResultData.Success(true)
            } else {
                throw Exception("Film didn't delete at all")
            }
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}