package com.kliachenko.dashboard

import com.kliachenko.core.Core
import com.kliachenko.core.HandleError
import com.kliachenko.core.modules.Clear
import com.kliachenko.core.modules.Module
import com.kliachenko.dashboard.data.DashboardRepositoryImpl
import com.kliachenko.dashboard.data.cache.CategoryCacheDataSource
import com.kliachenko.dashboard.data.cache.DashboardCacheDataSource
import com.kliachenko.dashboard.domain.DashboardInteractor
import com.kliachenko.dashboard.presentation.BaseDashboardResultMapper
import com.kliachenko.dashboard.presentation.BaseDashboardUiMapper
import com.kliachenko.dashboard.presentation.DashboardCommunication
import com.kliachenko.dashboard.presentation.DashboardViewModel
import com.kliachenko.dashboard.presentation.TabIdToCategoryMapper
import com.kliachenko.data.cache.FavoritesCacheDataSource
import com.kliachenko.data.cloud.FilmsCloudDataSource
import com.kliachenko.data.cloud.FilmsService

class DashboardModule(
    private val core: Core,
    private val clear: Clear,
) : Module<DashboardViewModel> {

    override fun provideViewModel(): DashboardViewModel {
        return DashboardViewModel(
            interactor = DashboardInteractor.Base(
                repository = DashboardRepositoryImpl(
                    cloudDataSource = FilmsCloudDataSource.Base(
                        core.provideMakeService().create(FilmsService::class.java)
                    ),
                    dashboardCacheDataSource = DashboardCacheDataSource.Base(
                        core.provideDataBase().filmsDao()
                    ),
                    favoritesCacheDataSource = FavoritesCacheDataSource.Base(
                        core.provideDataBase().favoriteDao()
                    ),
                    categoryCacheDataSource = CategoryCacheDataSource.Base(
                        core.provideDataBase().categoryDao()
                    ),
                    handleError = HandleError.Base(core.provideManageResources())
                )
            ),
            communication = DashboardCommunication.Base(),
            clear = clear,
            uiMapper = BaseDashboardResultMapper(BaseDashboardUiMapper()),
            categoryMapper = TabIdToCategoryMapper.Base,
            core.provideRunAsync()
        )
    }
}