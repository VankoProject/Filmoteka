package com.kliachenko.core

import android.view.View
import com.google.android.material.snackbar.Snackbar

class SnackBarWrapper {

    fun show(view: View, isFavorite: Boolean) {
        val message = if (isFavorite)
            R.string.added_to_favorites
        else R.string.removed_from_favorites
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}