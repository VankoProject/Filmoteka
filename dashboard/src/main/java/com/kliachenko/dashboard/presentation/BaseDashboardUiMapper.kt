package com.kliachenko.dashboard.presentation

import com.kliachenko.dashboard.presentation.adapter.DashboardUi
import com.kliachenko.domain.DashboardItem

class BaseDashboardUiMapper: DashboardItem.Mapper<DashboardUi> {

    override fun mapItems(
        id: Int,
        overview: String,
        posterPath: String,
        releaseDate: String,
        title: String,
        voteAverage: Double,
    ) = DashboardUi.Film(
        filmId = id,
        overView = overview,
        imageUrl = posterPath,
        releaseDate = releaseDate,
        title = title,
        rate = voteAverage,
        isFavorite = false
    )
}