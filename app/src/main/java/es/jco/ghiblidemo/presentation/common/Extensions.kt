package es.jco.ghiblidemo.presentation.common

import android.content.Context
import android.content.DialogInterface
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