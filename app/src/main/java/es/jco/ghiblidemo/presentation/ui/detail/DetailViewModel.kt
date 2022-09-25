package es.jco.ghiblidemo.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jco.data.common.ResultData
import es.jco.domain.Film
import es.jco.usecases.GetFilmByIdUseCase
import es.jco.usecases.SaveFilmUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val requestDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getFilmByIdUseCase: GetFilmByIdUseCase,
    private val saveFilmUseCase: SaveFilmUseCase
) : ViewModel() {
    
    companion object {
        private val TAG = DetailViewModel::class.qualifiedName
    }
    
    /** StateFlow to indicate when io thread is loading data */
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading

    /** StateFlow to indicate the selected film */
    private val _film = MutableStateFlow(Film())
    val film: StateFlow<Film> get() = _film

    /** StateFlow to indicate when inputs can be edited */
    private val _isInputEnabled = MutableStateFlow(false)
    val isInputEnabled: StateFlow<Boolean> get() = _isInputEnabled

    /** StateFlow to indicate when film has been saved */
    private val _isFilmSaved = MutableStateFlow(false)
    val isFilmSaved: StateFlow<Boolean> get() = _isFilmSaved

    /** Variable to save the modified data */
    var filmEditable = Film()

    /**
     * Request to get film by id from database
     *
     * @param filmId
     */
    fun loadFilm(filmId: String?) {
        if (filmId.isNullOrEmpty()) {
            Log.e(TAG, "Not found film saved - Go back to previous activity")
        }

        Log.i(TAG, "Loading film - $filmId")

        // Request launched in io thread
        viewModelScope.launch(requestDispatcher) {
            _loading.emit(true)
            when (val resultData = getFilmByIdUseCase.invoke(filmId!!)) {
                is ResultData.Success -> onSuccessGetFilm(resultData.value)
                is ResultData.Failure -> onErrorGetFilm(resultData.throwable)
            }
        }
    }

    /**
     * Function to handle when getFilmByID request succeeds
     *
     * @param film Selected film
     */
    private fun onSuccessGetFilm(film: Film) {
        viewModelScope.launch(requestDispatcher)  {
            _film.emit(film)
            filmEditable = film
        }
    }

    /**
     * Function to handle when getFilmByID request fails
     *
     * @param throwable Exception
     */
    private fun onErrorGetFilm(throwable: Throwable) {
        Log.e(TAG, if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage)
        viewModelScope.launch {
            _isInputEnabled.emit(false)
        }
    }

    /**
     * Request to save film by id on server and database
     *
     */
    fun saveFilm() {
        Log.i(TAG, "Detail ViewModel - Saving film: $filmEditable")
        // Request launched in io thread
        viewModelScope.launch(requestDispatcher) {
            _loading.emit(true)

            when (val resultData = saveFilmUseCase.invoke(filmEditable)) {
                is ResultData.Success -> onSuccessSaveFilm(resultData.value)
                is ResultData.Failure -> onErrorSaveFilm(resultData.throwable)
            }
        }
    }

    /**
     * Function to handle when saveFilm request succeeds
     *
     * @param success Saved film
     */
    private fun onSuccessSaveFilm(success: Boolean) {
        viewModelScope.launch {
            _loading.emit(false)
            _isInputEnabled.emit(false)
            _isFilmSaved.emit(success)
        }
    }

    /**
     * Function to handle when saveFilm request fails
     *
     * @param throwable Exception
     */
    private fun onErrorSaveFilm(throwable: Throwable) {
        Log.e(TAG, if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage)
        viewModelScope.launch {
            _isInputEnabled.emit(false)
            _loading.emit(false)
        }
    }

    /** Function to emit the status of inputs to enabled */
    fun enableInput() = viewModelScope.launch { _isInputEnabled.emit(true) }
    /** Function to emit the status of inputs to disabled */
    fun disableInput() = viewModelScope.launch { _isInputEnabled.emit(false) }

    /** Function to emit the state of loading */
    fun loading() = viewModelScope.launch { _loading.emit(true) }
    /** Function to emit the state of finish loading */
    fun finishLoading() = viewModelScope.launch { _loading.emit(false) }
}