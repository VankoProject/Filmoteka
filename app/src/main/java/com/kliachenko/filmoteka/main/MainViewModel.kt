package com.kliachenko.filmoteka.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kliachenko.core.Communication
import com.kliachenko.filmoteka.navigation.NavigationCommunication

class MainViewModel(
    private val navigationCommunication: NavigationCommunication,
    private val communication: MainCommunication,
    private val bottomScreens: List<Int>
) : ViewModel(), Communication.Read<MainUiState> {

    override fun liveData(): LiveData<MainUiState> = communication.liveData()


    fun init(firstRun: Boolean) {
        if(firstRun) {
            communication.update(MainUiState.Main)
        }
    }

    fun switchTab(selectedItem: Int) {
        navigationCommunication.navigateTo(bottomScreens[selectedItem])
    }

}