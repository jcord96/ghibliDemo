package es.jco.ghiblidemo.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import es.jco.domain.Film
import es.jco.ghiblidemo.R
import es.jco.ghiblidemo.databinding.ActivityDetailBinding
import es.jco.ghiblidemo.presentation.common.KEY_FILM
import es.jco.ghiblidemo.presentation.common.toHourText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Detail activity
 *
 * @constructor Create empty Detail activity
 */
@AndroidEntryPoint
class DetailActivity(
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : AppCompatActivity() {

    companion object {
        private val TAG = DetailActivity::class.qualifiedName
    }

    /** View model */
    private val viewModel: DetailViewModel by viewModels()

    /** View binding */
    private lateinit var binding: ActivityDetailBinding

    /**
     * On create
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        lifecycleScope.launch(mainDispatcher) {
            // Request to load the selected film
            intent.extras?.let { viewModel.loadFilm(it.getString(KEY_FILM)) }

            // Set on fields the loaded film
            viewModel.film.onEach {
                Log.d(TAG, "Loading film: ${it.title}")
                bindingFilm(it)

                viewModel.finishLoading()
            }.launchIn(this)

            // Enable or disable the inputs for editing
            viewModel.isInputEnabled.onEach {
                enableTextInput(it, getString(R.string.detail_hint_title), binding.layoutFilmTitle)
                enableTextInput(it, getString(R.string.detail_hint_description), binding.layoutFilmDescription)
            }.launchIn(this)

            // Check when film has been saved
            viewModel.isFilmSaved.onEach {
                if (it) finish()
            }.launchIn(this)

            // Setting the status of the progress bar
            viewModel.loading.onEach {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }.launchIn(this)
        }
    }

    /**
     * On create options menu
     *
     * @param menu Menu
     * @return
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)

        lifecycleScope.launch(mainDispatcher) {
            // Depending the inputs status, it's visible the edit or save icon
            viewModel.isInputEnabled.onEach {
                menu?.findItem(R.id.button_edit)?.isVisible = !it
                menu?.findItem(R.id.button_save)?.isVisible = it
            }.launchIn(this)
        }

        return true
    }

    /**
     * On options item selected
     *
     * @param item Selected menu item
     */
    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.button_edit -> { viewModel.enableInput(); true }
        R.id.button_save -> { saveData(); true }
        else -> super.onOptionsItemSelected(item)
    }


    /**
     * On support navigate up
     *
     * @return
     */
    override fun onSupportNavigateUp(): Boolean {
        Log.i(TAG, "Lifecycle - BackPressed")

        // Force the return to main activity
        onBackPressed()
        return true
    }

    /**
     * Function for saving data changes
     *
     */
    private fun saveData() {
        Log.i(TAG, "Binding data to inputs")
        with(binding) {
            viewModel.filmEditable.title = filmTitle.text.toString()
            viewModel.filmEditable.description = filmDescription.text.toString()
        }

        viewModel.saveFilm()
    }

    /**
     * Function for binding the film data
     *
     * @param film
     */
    private fun bindingFilm(film: Film) {
        with(binding) {
            Glide.with(this@DetailActivity).load(film.movieBanner).into(filmBanner)

            filmTitle.setText(film.title)
            filmDescription.setText(film.description)

            filmScore.rating = film.rtScore?.toFloat()?.div(2F)?.div(10F) ?: 0F
            filmReleaseDate.text = film.releaseDate.toString()
            filmRunningTime.text = film.runningTime?.toHourText()
        }
    }

    /**
     * Function for setting enable the input text
     *
     * @param enable
     * @param hint
     * @param inputLayout
     */
    private fun enableTextInput(enable: Boolean, hint: String, inputLayout: TextInputLayout) {
        inputLayout.isEnabled = enable
        inputLayout.boxBackgroundMode = if(enable) TextInputLayout.BOX_BACKGROUND_FILLED else TextInputLayout.BOX_BACKGROUND_NONE
        inputLayout.hint = if(enable) hint else null
    }
}