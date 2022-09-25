package es.jco.ghiblidemo.presentation.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.jco.ghiblidemo.R
import es.jco.ghiblidemo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
@AndroidEntryPoint
class MainActivity(
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.qualifiedName
    }

    /** View model */
    private val viewModel: MainViewModel by viewModels()

    /** View binding */
    private lateinit var binding: ActivityMainBinding

    /** Adapter for recycler view initialized by lazy */
    private val adapter by lazy {
        RecyclerViewAdapter { film, enableDelete ->
            if (enableDelete) {
                Log.i(TAG, "Deleting film: ${film.title}")
                viewModel.onDeleteFilm(film)
            } else {
                Log.i(TAG, "Selected film: ${film.title}")
                // TODO: Next activity
            }
        }
    }

    /**
     * On create
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Bound adapter to recycler view of the main layout
        binding.recycler.adapter = adapter

        // Binding stateFlows to views to automatically update values
        lifecycleScope.launch(mainDispatcher) {
            viewModel.loading.onEach {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }.launchIn(this)

            viewModel.films.onEach { adapter.films = it }.launchIn(this)

            viewModel.deletingFilm.onEach { adapter.enableDelete(it) }.launchIn(this)
        }

        // Request to get all films from database
        viewModel.getFilms()
    }

    /**
     * On create options menu
     *
     * @param menu
     * @return
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true;
    }

    /**
     * On options item selected
     *
     * @param item
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.button_delete -> {
                viewModel.onEnableDelete()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}