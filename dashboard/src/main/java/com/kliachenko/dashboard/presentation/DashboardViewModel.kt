package com.kliachenko.dashboard.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.kliachenko.core.BaseViewModel
import com.kliachenko.core.Observe
import com.kliachenko.core.RunAsync
import com.kliachenko.core.modules.Clear
import com.kliachenko.dashboard.domain.DashboardInteractor
import com.kliachenko.dashboard.domain.LoadResult
import com.kliachenko.dashboard.presentation.adapter.ClickActions
import com.kliachenko.dashboard.presentation.adapter.DashboardUi

class DashboardViewModel(
    private val interactor: DashboardInteractor,
    private val communication: DashboardCommunication,
    private val clear: Clear,
    private val uiMapper: LoadResult.Mapper<DashboardUiState>,
    private val categoryMapper: TabIdToCategoryMapper,
    runAsync: RunAsync,
) : BaseViewModel(runAsync), ClickActions, Clear, Observe<DashboardUiState> {

    private var currentTabPosition = 0

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
        }) { films ->
            communication.update(films.map(uiMapper))
        }
    }

    fun loadMore() {
        val category = categoryMapper.map(currentTabPosition)
        runAsync({
            interactor.loadMoreFilms(category)
        }) { films->
            communication.update(films.map(uiMapper))
        }
    }

    override fun retry() {
        load(currentTabPosition)
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

    override fun clear(viewModelClass: Class<out ViewModel>) {
        clear.clear(DashboardViewModel::class.java)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<DashboardUiState>) {
        communication.observe(owner, observer)
    }

}