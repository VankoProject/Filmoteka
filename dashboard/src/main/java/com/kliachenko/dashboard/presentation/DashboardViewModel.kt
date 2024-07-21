package com.kliachenko.dashboard.presentation

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
        }
    }

    fun load(tabPosition: Int) {
        currentTabPosition = tabPosition
        val category = categoryMapper.map(tabPosition)
        communication.update(DashboardUiState.Progress)
        runAsync({
            interactor.filmsByCategory(category)
        }) {
            val result: DashboardUiState = it.map(mapper)
            communication.update(result)
        }
    }

    override fun retry() {
        load(currentTabPosition)
    }

    override fun changeStatus(item: DashboardUi) {
        runAsync({
            interactor.addToFavorite(item.id().toInt())
        }) {}
    }

    override fun openDetail() {
        clear.clear(DashboardViewModel::class.java)
    }

}