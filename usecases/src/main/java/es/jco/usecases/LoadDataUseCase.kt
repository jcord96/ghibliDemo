package es.jco.usecases

import es.jco.data.common.ResultData
import es.jco.data.repository.*

class LoadDataUseCase(
    private val filmRepository: FilmRepository,
    private val locationRepository: LocationRepository,
    private val peopleRepository: PeopleRepository,
    private val speciesRepository: SpeciesRepository,
    private val vehicleRepository: VehicleRepository,
) {

    suspend operator fun invoke(): List<ResultData<Boolean>> = listOf(
        filmRepository.loadFilms(),
        locationRepository.loadLocations(),
        peopleRepository.loadPeople(),
        speciesRepository.loadSpecies(),
        vehicleRepository.loadVehicles(),
    )

}