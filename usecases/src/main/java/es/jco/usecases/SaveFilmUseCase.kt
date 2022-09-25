package es.jco.usecases

import es.jco.data.common.ResultData
import es.jco.data.repository.FilmRepository
import es.jco.domain.Film

class SaveFilmUseCase (private val filmRepository: FilmRepository) {

    suspend operator fun invoke(film: Film): ResultData<Boolean> = filmRepository.saveFilm(film)
}