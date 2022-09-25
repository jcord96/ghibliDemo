package es.jco.usecases

import es.jco.data.common.ResultData
import es.jco.data.repository.FilmRepository
import es.jco.domain.Film
import kotlinx.coroutines.flow.Flow

class GetFilmsUseCase(private val filmRepository: FilmRepository) {

    suspend operator fun invoke(): ResultData<Flow<List<Film>>> = filmRepository.getFilms()
}