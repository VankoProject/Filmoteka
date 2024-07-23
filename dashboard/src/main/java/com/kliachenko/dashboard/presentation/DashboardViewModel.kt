package com.kliachenko.dashboard.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import com.kliachenko.core.BaseViewModel
import com.kliachenko.core.RunAsync
import com.kliachenko.core.modules.Clear
import com.kliachenko.dashboard.domain.DashboardInteractor
import com.kliachenko.dashboard.domain.DashboardResult
import com.kliachenko.dashboard.presentation.adapter.ClickActions
import com.kliachenko.dashboard.presentation.adapter.DashboardUi
import kotlin.properties.Delegates

class DashboardViewModel(
    private val interactor: DashboardInteractor,
    private val communication: DashboardCommunication,
    private val clear: Clear,
    private val mapper: DashboardResult.Mapper<DashboardUiState>,
    private val categoryMapper: TabIdToCategoryMapper,
    runAsync: RunAsync,
) : BaseViewModel(runAsync), ClickActions {

    private var currentTabPosition by Delegates.notNull<Int>()

    fun liveData(): LiveData<DashboardUiState> = communication.liveData()

    fun init(firstRun: Boolean, tabPosition: Int) {
        if (firstRun) {
            load(tabPosition)
            Log.d("Filmateka", "VM init")
        }
    }

    fun load(tabPosition: Int) {
        Log.d("Filmateka", "VM load")
        currentTabPosition = tabPosition
        val category = categoryMapper.map(tabPosition)
        communication.update(DashboardUiState.Progress)
        runAsync({
            interactor.filmsByCategory(category)
        }) { films ->
            val result: DashboardUiState = films.map(mapper)
            communication.update(result)
        }
    }

    override fun retry() {
        load(currentTabPosition)
    }

    override fun openDetail() {
        clear.clear(DashboardViewModel::class.java)
    }

    override fun remove(item: DashboardUi) {
        runAsync({
            interactor.removeFromFavorites(item.filmId())
        }) {
            val updateState = communication.liveData().value?.updateFilmState(item)
            updateState?.let { newState ->
                communication.update(newState)
            }
        }
    }

    override fun add(item: DashboardUi) {
        runAsync({
            interactor.addToFavorite(item.filmId())
        }) {
            val updateState = communication.liveData().value?.updateFilmState(item)
            updateState?.let { newState ->
                communication.update(newState)
            }
        }
    }

}