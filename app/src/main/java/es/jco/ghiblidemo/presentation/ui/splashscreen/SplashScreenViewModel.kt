package es.jco.ghiblidemo.presentation.ui.splashscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jco.data.common.ResultData
import es.jco.ghiblidemo.presentation.common.State
import es.jco.usecases.LoadDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Splash screen view model
 *
 * @constructor Create empty Splash screen view model
 */
@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase) : ViewModel() {

    companion object {
        private val TAG = SplashScreenViewModel::class.qualifiedName
    }

    private val _uiState = MutableStateFlow<State<Boolean>?>(null)
    val  uiState: StateFlow<State<Boolean>?> = _uiState

    init {
        Log.i(TAG, "loading data")
        loadData()
    }

    /**
     * Function to load the users from server and save them in db
     *
     */
    private fun loadData() = viewModelScope.launch {
        // During screen startup, data is preloaded to streamline requests
        _uiState.emit(State.OnLoading)

        _uiState.emit(when (val resultData = loadDataUseCase.invoke().find { !(it as ResultData.Success).value || it is ResultData.Failure }) {
            is ResultData.Success -> State.OnSuccess(resultData.value)
            is ResultData.Failure -> State.OnFailure(resultData.throwable)
            else -> State.OnSuccess(true)
        })
    }
}