package com.kliachenko.detail.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.kliachenko.core.BaseViewModel
import com.kliachenko.core.Observe
import com.kliachenko.core.RunAsync
import com.kliachenko.core.modules.Clear
import com.kliachenko.detail.domain.DetailInteractor
import com.kliachenko.detail.domain.LoadResult

class DetailViewModel(
    private val interactor: DetailInteractor,
    private val communication: DetailCommunication,
    private val navigation: NavigationCommunication,
    private val uiMapper: LoadResult.Mapper<DetailUiState>,
    private val clear: Clear,
    runAsync: RunAsync,
) : BaseViewModel(runAsync), Clear, Observe<DetailUiState> {

    private var currentFilmId: Int = -1

    override fun observe(owner: LifecycleOwner, observer: Observer<DetailUiState>) {
        communication.observe(owner, observer)
    }

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<Unit>) {
        navigation.observe(owner, observer)
    }


    fun init(filmId: Int) {
        currentFilmId = filmId
        communication.update(DetailUiState.Progress)
        runAsync({
            interactor.filmDetail(filmId)
        }) { result ->
            communication.update(result.map(uiMapper))
        }
    }

    fun retry() {
        init(filmId = currentFilmId)
    }

    fun changeStatus(filmId: Int) {
        runAsync({
            interactor.isFavorite(filmId)
        }) { isFavorite ->
            if(isFavorite) {
                runAsync({
                    interactor.removeFromFavorite(filmId)
                }) {
                    val currentState = communication.liveData().value!!
                    communication.update(currentState.updateFilmStatus(false))
                }
            } else {
                runAsync({
                    interactor.addToFavorite(filmId)
                }) {
                    val currentState = communication.liveData().value!!
                    communication.update(currentState.updateFilmStatus(true))
                }
            }
        }
    }

    fun goBack() {
        navigation.update(Unit)
    }

    override fun clear(viewModelClass: Class<out ViewModel>) {
        clear.clear(viewModelClass)
    }

}
