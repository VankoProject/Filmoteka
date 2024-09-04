package com.kliachenko.detail

import com.kliachenko.core.Core
import com.kliachenko.core.HandleError
import com.kliachenko.core.modules.Clear
import com.kliachenko.core.modules.Module
import com.kliachenko.data.cache.FavoritesCacheDataSource
import com.kliachenko.data.cloud.FilmsCloudDataSource
import com.kliachenko.data.cloud.FilmsService
import com.kliachenko.detail.data.DetailRepositoryImpl
import com.kliachenko.detail.domain.DetailInteractor
import com.kliachenko.detail.presentation.BaseDetailResultMapper
import com.kliachenko.detail.presentation.DetailCommunication
import com.kliachenko.detail.presentation.DetailUiMapper
import com.kliachenko.detail.presentation.DetailViewModel

class DetailModule(
    private val core: Core,
    private val clear: Clear,
) : Module<DetailViewModel> {
    override fun provideViewModel(): DetailViewModel {
        return DetailViewModel(
            interactor = DetailInteractor.Base(
                DetailRepositoryImpl(
                    cloudDataSource = FilmsCloudDataSource.Base(
                        core.provideMakeService().create(
                            FilmsService::class.java
                        )
                    ),
                    favoritesCacheDataSource = FavoritesCacheDataSource.Base(
                        core.provideDataBase().favoriteDao()
                    ),
                    handleError = HandleError.Base(core.provideManageResources())
                )
            ),
            communication = DetailCommunication.Base(),
            uiMapper = BaseDetailResultMapper(DetailUiMapper()),
            clear = clear,
            runAsync = core.provideRunAsync()
        )
    }
}