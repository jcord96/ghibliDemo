package es.jco.usecases

import es.jco.data.common.ResultData
import es.jco.data.repository.FilmRepository

class DeleteFilmUseCase(private val filmRepository: FilmRepository) {

    suspend operator fun invoke(filmId: String): ResultData<Boolean> = filmRepository.deleteFilm(filmId)
}