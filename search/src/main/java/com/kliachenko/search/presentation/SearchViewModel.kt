package com.kliachenko.search.presentation

import androidx.lifecycle.ViewModel
import com.kliachenko.core.BaseViewModel
import com.kliachenko.core.ManageResource
import com.kliachenko.core.RunAsync
import com.kliachenko.core.modules.Clear
import com.kliachenko.search.domain.LoadResult
import com.kliachenko.search.domain.SearchInteractor
import com.kliachenko.search.presentation.adapter.ClickActions

class SearchViewModel(
    private val interactor: SearchInteractor,
    private val communication: SearchCommunication,
    private val uiMapper: LoadResult.Mapper<SearchUiState>,
    private val manageResource: ManageResource.Search,
    private val clear: Clear,
    runAsync: RunAsync,
) : BaseViewModel(runAsync), Clear, ClickActions {

    private var lastQuery: String = ""

    fun init() {
        runAsync({
            interactor.loadHistory()
        }) { result ->
            if (result.isEmpty())
                communication.update(SearchUiState.Initial(manageResource.initialText()))
            else {
                val historyItems = uiMapper.mapSuccess(result)
                communication.update(historyItems)
            }
        }
    }

    fun search(query: String) {
        lastQuery = query
        if (query.length < 2) {
            communication.update(SearchUiState.Progress(manageResource.continueText()))
        }
        else
            communication.update(SearchUiState.Progress(manageResource.searchingText()))
            runAsync({
                interactor.search(query)
            }) { result ->
                val items = result.map(uiMapper)
                communication.update(items)
            }
    }

    override fun retry() {
        search(lastQuery)
    }


    override fun clear(viewModelClass: Class<out ViewModel>) {
        clear.clear(this.javaClass)
    }

}