package es.jco.usecases

import es.jco.data.common.ResultData
import es.jco.data.repository.FilmRepository
import es.jco.domain.Film

class GetFilmByIdUseCase (private val filmRepository: FilmRepository) {

    suspend operator fun invoke(filmId: String): ResultData<Film> = filmRepository.getFilm(filmId)
}