package es.jco.ghiblidemo.presentation.common

import android.content.Context
import android.content.DialogInterface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog

/**
 * Inflate layouts
 *
 * @param id
 * @return
 */
fun ViewGroup.inflate(@LayoutRes id: Int): View = LayoutInflater.from(this.context).inflate(id, this, false)

/**
 * Show error dialog
 *
 * @param message
 * @param acceptButton
 * @param acceptListener
 */
fun Context.showErrorDialog(message: Int, acceptButton: Int, acceptListener: DialogInterface.OnClickListener) = AlertDialog.Builder(this)
    .setMessage(message)
    .setPositiveButton(acceptButton, acceptListener)
    .setCancelable(false)
    .create()
    .show()

fun Context.toPx(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun View.translateYBy(
    alpha: Float = 0f,
    translateBy: Float = 50f,
    duration: Long = 0L,
    startingDelay: Long = 5
) {
    animate().alpha(alpha).translationYBy(context.toPx(translateBy))
        .setStartDelay(startingDelay)
        .setDuration(duration).start()
}