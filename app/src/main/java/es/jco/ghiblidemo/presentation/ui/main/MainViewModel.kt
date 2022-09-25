package es.jco.ghiblidemo.presentation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jco.data.common.ResultData
import es.jco.domain.Film
import es.jco.usecases.DeleteFilmUseCase
import es.jco.usecases.GetFilmsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main view model
 *
 * @constructor Create empty Main view model
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val requestDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getFilmsUseCase: GetFilmsUseCase,
    private val deleteFilmUseCase: DeleteFilmUseCase
    ) : ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.qualifiedName
    }

    /** StateFlow to indicate when io thread is loading data */
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading

    /** StateFlow to indicate all films in live from database */
    private val _films = MutableStateFlow(emptyList<Film>())
    val films: StateFlow<List<Film>> get() = _films

    /** StateFlow to indicate when film can delete films */
    private val _deletingFilm = MutableStateFlow(false)
    val deletingFilm: StateFlow<Boolean> get() = _deletingFilm

    /**
     * Request to get films from database
     */
    fun getFilms() {
        Log.i(TAG, "Getting films - Loading")
        // Request launched in io thread
        viewModelScope.launch(requestDispatcher) {

            // The progress bar is activated to indicate that there is a process loading
            loading()

            when (val resultData = getFilmsUseCase.invoke()) {
                is ResultData.Success -> onSuccessGetFilms(resultData.value)
                is ResultData.Failure -> onErrorGetFilms(resultData.throwable)
            }
        }
    }

    /**
     * Function to handle when getFilms request succeeds
     *
     * @param value Flowable with returned films list
     */
    private suspend fun onSuccessGetFilms(value: Flow<List<Film>>) {
        Log.i(TAG, "Getting films - Successfully ")

        // A flow is returned to get all films every time that there are changes to the film entity
        // When a film is created, updated or deleted, this flow will be notified and the recycler view list will update

        // Request launched in io thread
        viewModelScope.launch {
            value.onEach { _films.emit(it) }.launchIn(this)

            // The progress bar turns off when the request response is returned
            finishLoading()
        }
    }

    /**
     * Function to handle when getFilms request fails
     *
     * @param throwable Exception
     */
    private fun onErrorGetFilms(throwable: Throwable) {
        Log.e(TAG, "Getting films - Error thrown")
        Log.e(TAG, "Cause error: ${throwable.localizedMessage}")

        // The progress bar turns off when the request response is returned
        finishLoading()
    }

    fun onEnableDelete() {
        viewModelScope.launch(requestDispatcher) {
            Log.i(TAG, "Setting ${if (deletingFilm.value) "enable" else "disable"} deleting film")
            _deletingFilm.emit(!deletingFilm.value)
        }
    }

    fun onDeleteFilm(film: Film) {
        if (film.id.isNullOrEmpty()) {
            Log.e(TAG, "Tried to delete a film without ID: ${film.title}")
            return
        }
            
        viewModelScope.launch(requestDispatcher) {
            // The progress bar is activated to indicate that there is a process loading
            loading()
            
            when (val resultData = deleteFilmUseCase.invoke(film.id!!)) {
                is ResultData.Success -> onSuccessDeleteFilm(resultData.value)
                is ResultData.Failure -> onErrorDeleteFilm(resultData.throwable)
            }
            
            // The progress bar turns off when the request response is returned
            finishLoading()
        }
    }


    /**
     * Function to handle when deleteFilm request succeeds
     *
     * @param value Boolean with result
     */
    private fun onSuccessDeleteFilm(value: Boolean) {
        Log.i(TAG, "Deleting film - $${if (value) "Successfully" else "Failed"}")
    }

    /**
     * Function to handle when deleteFilm request fails
     *
     * @param throwable Exception
     */
    private fun onErrorDeleteFilm(throwable: Throwable) {
        Log.e(TAG, "Deleting film - Error thrown")
        Log.e(TAG, "Cause error: ${throwable.localizedMessage}")
    }
    
    
    /** Function to emit the state of loading */
    private fun loading() = viewModelScope.launch { _loading.emit(true) }
    /** Function to emit the state of finish loading */
    private fun finishLoading() = viewModelScope.launch { _loading.emit(false) }

}