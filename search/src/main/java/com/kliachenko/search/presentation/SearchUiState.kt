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

    data class Progress(private val progressMessage: String) : SearchUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(SearchUi.Progress(progressMessage)))
        }
    }

    data class Initial(private val titleMessage: String) : SearchUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(SearchUi.Initial(titleMessage)))
        }
    }

    object Empty: SearchUiState {
        override fun updateAdapter(adapter: ShowList) = Unit
    }

}
