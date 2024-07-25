package com.kliachenko.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

interface NavigationCommunication {

    fun navigateTo(destinationId: Int)

    fun navigateWithArgs(directions: NavDirections)

    fun popBackStack(): Boolean

    class Base(
        private val navController: NavController,
    ) : NavigationCommunication {

        override fun navigateTo(destinationId: Int) {
            navController.navigate(destinationId)
        }

        override fun navigateWithArgs(directions: NavDirections) {
            navController.navigate(directions)
        }

        override fun popBackStack(): Boolean = navController.popBackStack()

    }

}