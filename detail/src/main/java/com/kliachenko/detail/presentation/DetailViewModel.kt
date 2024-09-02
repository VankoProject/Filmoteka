package com.kliachenko.detail.presentation

import androidx.lifecycle.ViewModel
import com.kliachenko.core.BaseViewModel
import com.kliachenko.core.RunAsync
import com.kliachenko.core.modules.Clear
import com.kliachenko.detail.domain.DetailInteractor
import com.kliachenko.detail.domain.LoadResult

class DetailViewModel(
    private val interactor: DetailInteractor,
    private val communication: DetailCommunication,
    private val uiMapper: LoadResult.Mapper<DetailUiState>,
    private val clear: Clear,
    runAsync: RunAsync
): BaseViewModel(runAsync), Clear {

    fun init(filmId: Int) {

    }

    fun retry() {

    }

    fun add(filmDetail: FilmDetailUi) {

    }

    fun remove(filmId: Int) {

    }

    fun goBack() {

    }

    override fun clear(viewModelClass: Class<out ViewModel>) {
        clear.clear(viewModelClass)
    }

    fun changeStatus(filmId: Int) {

    }

}
