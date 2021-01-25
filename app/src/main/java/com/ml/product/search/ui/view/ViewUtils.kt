package com.ml.product.search.ui.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes

const val POSITION_ZERO = 0

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

/**
 * This method to show a default Alert Dialog for errors
 */
fun showErrorMessage(context: Context, title: String, message: String) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setNeutralButton(android.R.string.ok, null)
        .create()
        .show()
}

/**
 * This method hide the key board
 */
fun hideSoftKeyboard(activity: Activity?) {
    val view = activity?.currentFocus
    view?.let { v ->
        val imm =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager // or context
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}


