package com.kliachenko.filmoteka.matchers

import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import java.util.concurrent.TimeoutException

fun waitUntilProgressIsNotDisplayed(targetViewId: Int, timeout: Long) =
    waitForView(targetViewId = targetViewId, viewMatcher = not(isDisplayed()), timeout = timeout)

fun waitForView(targetViewId: Int, viewMatcher: Matcher<View>, timeout: Long): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String {
            return "wait for a specific view with id $targetViewId; during $timeout millis"
        }

        override fun getConstraints(): Matcher<View> {
            return isRoot()
        }

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadUntilIdle()
            val startTime = System.currentTimeMillis()
            val endTime = startTime + timeout

            do {
                view.findViewById<View>(targetViewId)?.let { view ->
                    if (viewMatcher.matches(view)) return
                }
                uiController.loopMainThreadForAtLeast(100)
            } while (System.currentTimeMillis() < endTime)

            throw PerformException.Builder()
                .withCause(TimeoutException())
                .withActionDescription(this.description)
                .withViewDescription(HumanReadables.describe(view))
                .build()
        }

    }
}