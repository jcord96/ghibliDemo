package es.jco.ghiblidemo.presentation.ui.splashscreen

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.jco.ghiblidemo.R
import es.jco.ghiblidemo.databinding.ActivitySplashScreenBinding
import es.jco.ghiblidemo.presentation.common.State
import es.jco.ghiblidemo.presentation.common.showErrorDialog
import kotlinx.coroutines.launch

/**
 * Splash screen activity
 *
 * @constructor Create empty Splash screen activity
 */
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private val TAG = SplashScreenActivity::class.qualifiedName
    }

    /** View model */
    private val viewModel: SplashScreenViewModel by viewModels()

    /** View binding */
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {

            // The result of the request will finished in the next activity even so it doesn't download data
            viewModel.uiState.collect {
                Log.i(TAG,it.toString())

                when (it) {
                    State.OnLoading -> Unit // Do nothing
                    is State.OnSuccess -> startMainActivity()
                    is State.OnFailure -> failureLoadData(it.throwable)
                    else -> Unit // Do nothing
                }
            }
        }
    }


    /**
     * Private function to start the main activity
     */
    private fun startMainActivity() {
        // TODO: Next activity
    }

    /**
     * Private function to handle data load failures
     *
     * @param throwable failure received
     */
    private fun failureLoadData(throwable: Throwable) {
        Log.e(
            TAG,
            if (throwable.localizedMessage.isNullOrEmpty()) getString(R.string.error_unexpected) else throwable.localizedMessage
        )

        applicationContext.showErrorDialog(
            R.string.ad_message_error_loading_data,
            R.string.ad_continue
        ) { dialog, _ ->
            dialog.dismiss()
            startMainActivity()
        }
    }
}