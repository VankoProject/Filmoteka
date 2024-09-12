package com.kliachenko.filmoteka.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kliachenko.core.Communication

class MainViewModel(
    private val communication: MainCommunication,
) : ViewModel(), Communication.Read<MainUiState> {

    override fun liveData(): LiveData<MainUiState> = communication.liveData()

    fun init() {
       mainUiState()
    }

    fun secondaryUiState() {
        communication.update(MainUiState.Secondary)
    }

    fun mainUiState() {
        communication.update(MainUiState.Main)
    }

}