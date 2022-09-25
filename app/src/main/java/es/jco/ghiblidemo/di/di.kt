package es.jco.ghiblidemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import es.jco.ghiblidemo.presentation.ui.detail.DetailViewModel
import es.jco.ghiblidemo.presentation.ui.main.MainViewModel
import es.jco.ghiblidemo.presentation.ui.splashscreen.SplashScreenViewModel
import es.jco.usecases.*

/**
 * Splash screen activity module
 * This class declares how to provide the view model
 *
 * @constructor Create empty Splash screen activity module
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class SplashScreenActivityModule {

    @Provides
    fun splashScreenViewModelProvider(loadDataUseCase: LoadDataUseCase) = SplashScreenViewModel(loadDataUseCase)
}

/**
 * Main activity module
 * This class declares how to provide the view model
 *
 * @constructor Create empty Main activity module
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class MainActivityModule {

    @Provides
    fun mainViewModelProvider(getFilmsUseCase: GetFilmsUseCase,deleteFilmUseCase: DeleteFilmUseCase) = MainViewModel(getFilmsUseCase = getFilmsUseCase, deleteFilmUseCase = deleteFilmUseCase)
}

/**
 * Detail activity module
 * This class declares how to provide the view model
 *
 * @constructor Create empty Main activity module
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class DetailActivityModule {

    @Provides
    fun detailViewModelProvider(getFilmByIdUseCase: GetFilmByIdUseCase, saveFilmUseCase: SaveFilmUseCase) = DetailViewModel(getFilmByIdUseCase = getFilmByIdUseCase, saveFilmUseCase = saveFilmUseCase)
}