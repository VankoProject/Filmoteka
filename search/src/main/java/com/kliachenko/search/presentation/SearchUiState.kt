package com.kliachenko.search.presentation

import com.kliachenko.search.presentation.adapter.SearchUi
import com.kliachenko.search.presentation.adapter.ShowList

interface SearchUiState {

    fun updateAdapter(adapter: ShowList)

    data class Error(private val message: String) : SearchUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(SearchUi.Error(message)))
        }
    }

    data class Success(private val filmsList: List<SearchUi>) : SearchUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(filmsList)
        }
    }

    data class History(private val filmsList: List<SearchUi>) : SearchUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(filmsList)
        }
    }

    object Valid : SearchUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(SearchUi.Valid))
        }
    }

    object Invalid : SearchUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(SearchUi.Invalid))
        }
    }

    object Blank : SearchUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(SearchUi.Blank))
        }
    }

    object Initial : SearchUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(SearchUi.Initial))
        }
    }

}
