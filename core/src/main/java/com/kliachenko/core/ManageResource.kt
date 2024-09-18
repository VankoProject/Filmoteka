package com.kliachenko.core

import android.content.Context

interface ManageResource {

    interface LoadError {

        fun noInternetConnection(): String

        fun serviceUnavailable(): String
    }

    interface Search {

        fun initialText(): String

        fun noFoundInfoText(): String

        fun continueText(): String

        fun searchingText(): String

        fun recentText(): String

        fun searchResults(): String
    }

    interface Mutable: Search, LoadError


    class Base(private val context: Context) : Mutable {

        override fun noInternetConnection() =
            context.resources.getString(R.string.no_internet_connection)

        override fun serviceUnavailable() =
            context.resources.getString(R.string.service_unavailable)

        override fun initialText() =
            context.resources.getString(R.string.initial_search_message)

        override fun noFoundInfoText() =
            context.resources.getString(R.string.blank_search_message)

        override fun continueText() =
            context.resources.getString(R.string.continue_search_text)

        override fun searchingText() = context.resources.getString(R.string.search_text)

        override fun recentText() = context.resources.getString(R.string.recent)

        override fun searchResults() = context.resources.getString(R.string.search_results)

    }
}