package com.kliachenko.filmoteka.main

import android.view.View

interface MainUiState {

    fun updateState(bottomNavigation: View)

    object Main : MainUiState {
        override fun updateState(bottomNavigation: View) {
            bottomNavigation.visibility = View.VISIBLE
        }
    }

    object Secondary : MainUiState {
        override fun updateState(bottomNavigation: View) {
            bottomNavigation.visibility = View.GONE
        }
    }
}