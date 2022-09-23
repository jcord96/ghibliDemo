package es.jco.ghiblidemo.data.database

import es.jco.data.source.LocalDataSource
import es.jco.domain.*
import es.jco.ghiblidemo.data.database.entity.*
import es.jco.ghiblidemo.data.database.mapper.toEntity
import javax.inject.Inject

/**
 * Room as local data source
 *
 * @property appRoomDatabase
 * @constructor Create empty Room data source
 */
class RoomDataSource @Inject constructor(private val appRoomDatabase: AppRoomDatabase) : LocalDataSource {
    
    private val ghibliCrossRefDao = appRoomDatabase.ghibliCrossRefDao()
    private val filmDao = appRoomDatabase.filmDao()
    private val locationDao = appRoomDatabase.locationDao()
    private val peopleDao = appRoomDatabase.peopleDao()
    private val speciesDao = appRoomDatabase.speciesDao()
    private val vehicleDao = appRoomDatabase.vehicleDao()

    // region Functions for Films

    /**
     * Function to insert a film
     *
     * @param film
     */
    override suspend fun insertFilm(film: Film) {
        val filmEntity = film.toEntity()

        filmDao.insert( filmEntity )
        ghibliCrossRefDao.insertAllFilmPeopleCrossRef( film.people.filter { it.id != null }.map { FilmPeopleCrossRef(filmEntity.filmId, it.id!!) } )
        ghibliCrossRefDao.insertAllFilmLocationCrossRef( film.locations.filter { it.id != null }.map { FilmLocationCrossRef(filmEntity.filmId, it.id!!) } )
        ghibliCrossRefDao.insertAllFilmSpeciesCrossRef( film.species.filter { it.id != null }.map { FilmSpeciesCrossRef(filmEntity.filmId, it.id!!) } )
        ghibliCrossRefDao.insertAllFilmVehicleCrossRef( film.vehicles.filter { it.id != null }.map { FilmVehicleCrossRef(filmEntity.filmId, it.id!!) } )
    }

    /**
     * Function to insert a list of films
     *
     * @param films
     */
    override suspend fun insertFilms(films: List<Film>) {
        val filmList = ArrayList<FilmEntity>()
        val filmPeopleCrossRefList = ArrayList<FilmPeopleCrossRef>()
        val filmLocationCrossRefList = ArrayList<FilmLocationCrossRef>()
        val filmSpeciesCrossRefList = ArrayList<FilmSpeciesCrossRef>()
        val filmVehicleCrossRefList = ArrayList<FilmVehicleCrossRef>()

        films.forEach { filmToInsert ->
            val filmEntity = filmToInsert.toEntity()

            filmList.add(filmEntity)
            filmPeopleCrossRefList.addAll( filmToInsert.people.filter { it.id != null }.map { FilmPeopleCrossRef(filmEntity.filmId, it.id!!) } )
            filmLocationCrossRefList.addAll( filmToInsert.locations.filter { it.id != null }.map { FilmLocationCrossRef(filmEntity.filmId, it.id!!) } )
            filmSpeciesCrossRefList.addAll( filmToInsert.species.filter { it.id != null }.map { FilmSpeciesCrossRef(filmEntity.filmId, it.id!!) } )
            filmVehicleCrossRefList.addAll( filmToInsert.vehicles.filter { it.id != null }.map { FilmVehicleCrossRef(filmEntity.filmId, it.id!!) } )
        }

        filmDao.insertAll(filmList)
        ghibliCrossRefDao.insertAllFilmPeopleCrossRef( filmPeopleCrossRefList )
        ghibliCrossRefDao.insertAllFilmLocationCrossRef( filmLocationCrossRefList)
        ghibliCrossRefDao.insertAllFilmSpeciesCrossRef( filmSpeciesCrossRefList )
        ghibliCrossRefDao.insertAllFilmVehicleCrossRef( filmVehicleCrossRefList )

    }

    /**
     * Function for getting the count of films
     *
     * @return
     */
    override suspend fun countFilms(): Long = filmDao.countFilms()

    // endregion
    // region Functions for Location

    /**
     * Function to insert a location
     *
     * @param location
     */
    override suspend fun insertLocation(location: Location) {
        val locationEntity = location.toEntity()

        locationDao.insert(locationEntity)
        ghibliCrossRefDao.insertAllLocationPeopleCrossRef( location.residents.filter { it.id != null }.map { LocationPeopleCrossRef(locationEntity.locationId, it.id!!) })
        ghibliCrossRefDao.insertAllFilmLocationCrossRef( location.films.filter { it.id != null }.map { FilmLocationCrossRef(it.id!!, locationEntity.locationId ) } )
    }

    /**
     * Function to insert a list of locations
     *
     * @param locations
     */
    override suspend fun insertLocations(locations: List<Location>) {
        val locationList = ArrayList<LocationEntity>()
        val locationPeopleCrossRefList = ArrayList<LocationPeopleCrossRef>()
        val filmLocationCrossRefList = ArrayList<FilmLocationCrossRef>()

        locations.forEach { locationToInsert ->
            val locationEntity = locationToInsert.toEntity()

            locationList.add(locationEntity)
            locationPeopleCrossRefList.addAll( locationToInsert.residents.filter { it.id != null }.map { LocationPeopleCrossRef(locationEntity.locationId, it.id!!) })
            filmLocationCrossRefList.addAll( locationToInsert.films.filter { it.id != null }.map { FilmLocationCrossRef(it.id!!, locationEntity.locationId) } )
        }

        locationDao.insertAll(locationList)
        ghibliCrossRefDao.insertAllLocationPeopleCrossRef( locationPeopleCrossRefList )
        ghibliCrossRefDao.insertAllFilmLocationCrossRef( filmLocationCrossRefList )
    }

    /**
     * Function for getting the count of location
     *
     * @return
     */
    override suspend fun countLocations(): Long = locationDao.countLocations()

    // endregion
    // region Functions for People

    /**
     * Function to insert one person
     *
     * @param people
     */
    override suspend fun insertPeople(people: People) {
        val peopleEntity = people.toEntity()

        peopleDao.insert(peopleEntity)
        ghibliCrossRefDao.insertAllFilmPeopleCrossRef( people.films.filter { it.id != null }.map { FilmPeopleCrossRef(it.id!!, peopleEntity.peopleId) } )

    }

    /**
     * Function to insert a list of people
     *
     * @param people
     */
    override suspend fun insertPeople(people: List<People>) {
        val peopleList = ArrayList<PeopleEntity>()
        val filmPeopleCrossRefList = ArrayList<FilmPeopleCrossRef>()


        people.forEach { peopleToInsert ->
            val peopleEntity = peopleToInsert.toEntity()

            peopleList.add(peopleEntity)
            filmPeopleCrossRefList.addAll( peopleToInsert.films.filter { it.id != null }.map { FilmPeopleCrossRef(it.id!!, peopleEntity.peopleId) } )
        }

        peopleDao.insertAll(peopleList)
        ghibliCrossRefDao.insertAllFilmPeopleCrossRef( filmPeopleCrossRefList )
    }

    /**
     * Function for getting the count of people
     *
     * @return
     */
    override suspend fun countPeople(): Long = peopleDao.countPeople()

    // endregion
    // region Functions for Species

    /**
     * Function to insert one species
     *
     * @param species
     */
    override suspend fun insertSpecies(species: Species) {
        val speciesEntity = species.toEntity()

        speciesDao.insert(speciesEntity)
        ghibliCrossRefDao.insertAllFilmSpeciesCrossRef( species.films.filter { it.id != null }.map { FilmSpeciesCrossRef(it.id!!, speciesEntity.speciesId) } )
    }

    /**
     * Function to insert a list of species
     *
     * @param species
     */
    override suspend fun insertSpecies(species: List<Species>) {
        val speciesList = ArrayList<SpeciesEntity>()
        val filmSpeciesCrossRefList = ArrayList<FilmSpeciesCrossRef>()


        species.forEach { speciesToInsert ->
            val speciesEntity = speciesToInsert.toEntity()

            speciesList.add(speciesEntity)
            filmSpeciesCrossRefList.addAll( speciesToInsert.films.filter { it.id != null }.map { FilmSpeciesCrossRef(it.id!!, speciesEntity.speciesId) } )
        }

        speciesDao.insertAll(speciesList)
        ghibliCrossRefDao.insertAllFilmSpeciesCrossRef( filmSpeciesCrossRefList )
    }

    /**
     * Function for getting the count of species
     *
     * @return
     */
    override suspend fun countSpecies(): Long = speciesDao.countSpecies()

    // endregion
    // region Functions for Vehicle

    /**
     * Function to insert a vehicle
     *
     * @param vehicle
     */
    override suspend fun insertVehicle(vehicle: Vehicle) {
        val vehicleEntity = vehicle.toEntity()

        vehicleDao.insert(vehicleEntity)
        ghibliCrossRefDao.insertAllFilmVehicleCrossRef( vehicle.films.filter { it.id != null }.map { FilmVehicleCrossRef(it.id!!, vehicleEntity.vehicleId) } )
    }

    /**
     * Function to insert a list of vehicles
     *
     * @param vehicles
     */
    override suspend fun insertVehicles(vehicles: List<Vehicle>) {
        val vehicleList = ArrayList<VehicleEntity>()
        val filmVehicleCrossRefList = ArrayList<FilmVehicleCrossRef>()


        vehicles.forEach { vehicleToInsert ->
            val vehicleEntity = vehicleToInsert.toEntity()

            vehicleList.add(vehicleEntity)
            filmVehicleCrossRefList.addAll( vehicleToInsert.films.filter { it.id != null }.map { FilmVehicleCrossRef(it.id!!, vehicleEntity.vehicleId) } )
        }

        vehicleDao.insertAll(vehicleList)
        ghibliCrossRefDao.insertAllFilmVehicleCrossRef( filmVehicleCrossRefList )
    }

    /**
     * Function for getting the count of vehicles
     *
     * @return
     */
    override suspend fun countVehicles(): Long = vehicleDao.countVehicles()

    // endregion
}