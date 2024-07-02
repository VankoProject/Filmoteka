package com.kliachenko.filmoteka.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

interface NavigationCommunication {

    fun navigateTo(destinationId: Int)

    fun navigateTo(directions: NavDirections)

    fun popBackStack(): Boolean

    class Base(private val navController: NavController): NavigationCommunication {

        override fun navigateTo(destinationId: Int) {
            navController.navigate(destinationId)
        }

        override fun navigateTo(directions: NavDirections) {
            navController.navigate(directions)
        }

        override fun popBackStack(): Boolean {
            return navController.popBackStack()
        }
    }
}