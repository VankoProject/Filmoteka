package com.kliachenko.search

import com.kliachenko.core.Core
import com.kliachenko.core.HandleError
import com.kliachenko.core.modules.Clear
import com.kliachenko.core.modules.Module
import com.kliachenko.data.cloud.FilmsCloudDataSource
import com.kliachenko.data.cloud.FilmsService
import com.kliachenko.data.mapper.FilmSearchMapper
import com.kliachenko.search.data.SearchRepositoryImpl
import com.kliachenko.search.data.cache.SearchHistoryDataSource
import com.kliachenko.search.domain.SearchInteractor
import com.kliachenko.search.presentation.BaseSearchLoadResultMapper
import com.kliachenko.search.presentation.BaseSearchUiMapper
import com.kliachenko.search.presentation.SearchCommunication
import com.kliachenko.search.presentation.SearchViewModel

class SearchModule(
    private val core: Core,
    private val clear: Clear,
) : Module<SearchViewModel> {

    override fun provideViewModel(): SearchViewModel {
        return SearchViewModel(
            interactor = SearchInteractor.Base(
                repository = SearchRepositoryImpl(
                    searchDataSource = SearchHistoryDataSource.Base(
                        core.provideDataBase().searchHistoryDao()
                    ),
                    cloudDataSource = FilmsCloudDataSource.Base(
                        core.provideMakeService().create(
                            FilmsService::class.java
                        )
                    ),
                    mapToCache = FilmSearchMapper.ToCache.Base,
                    mapToDomain = FilmSearchMapper.ToDomain.Base,
                    manageResource = core.provideManageResources(),
                    handleError = HandleError.Base(core.provideManageResources())
                )
            ),
            communication = SearchCommunication.Base(),
            uiMapper = BaseSearchLoadResultMapper(
                BaseSearchUiMapper()
            ),
            manageResource = core.provideManageResources(),
            clear = clear,
            runAsync = core.provideRunAsync()
        )
    }
}