package com.kliachenko.filmoteka.main

import com.kliachenko.core.Communication

interface MainCommunication : Communication.Mutable<MainUiState> {

    object Base : Communication.Abstract<MainUiState>(), MainCommunication
}