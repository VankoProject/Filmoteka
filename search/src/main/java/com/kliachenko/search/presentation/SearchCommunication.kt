package com.kliachenko.search.presentation

import com.kliachenko.core.Communication

interface SearchCommunication : Communication.Mutable<SearchUiState> {

    class Base : Communication.Abstract<SearchUiState>(), SearchCommunication

}
