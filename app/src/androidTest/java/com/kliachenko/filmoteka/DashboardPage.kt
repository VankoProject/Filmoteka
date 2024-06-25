package com.kliachenko.filmoteka

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher

class DashboardPage {

    private val parentIdMatcher: Matcher<View> = withParent(withId(R.id.dashboardLayout))
    private val parentClassMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    fun checkDashboardProgressState(message: String, tabName: String) {
        TODO("Not yet implemented")
    }

    fun waitUntilDashboardProgressStateIsNotVisible() {
        TODO("Not yet implemented")
    }

    fun checkErrorState(errorMessage: String) {
        TODO("Not yet implemented")
    }

    fun tapRetryButton() {
        TODO("Not yet implemented")
    }

    fun checkSuccessfulState() {
        TODO("Not yet implemented")
    }

    fun tapTab(tabName: String) {
        TODO("Not yet implemented")
    }

    fun tapUnlikeBookmarkIcon() {
        TODO("Not yet implemented")
    }

    fun checkAddToFavorites() {
        TODO("Not yet implemented")
    }

    fun tapLikeBookmarkIcon() {
        TODO("Not yet implemented")
    }

    fun checkRemovedFromFavorites() {
        TODO("Not yet implemented")
    }


}
