package com.kliachenko.dashboard.presentation.adapter

import com.kliachenko.dashboard.databinding.DashboardErrorStateLayoutBinding
import com.kliachenko.dashboard.databinding.DashboardProgressStateLayoutBinding
import com.kliachenko.dashboard.databinding.FilmItemLayoutBinding
import com.kliachenko.dashboard.databinding.NodataLayoutBinding
import com.kliachenko.dashboard.databinding.PagingProgressLayoutBinding

interface DashboardUi {

    fun show(binding: FilmItemLayoutBinding) = Unit

    fun show(binding: DashboardErrorStateLayoutBinding) = Unit

    fun show(binding: DashboardProgressStateLayoutBinding) = Unit

    fun show(binding: PagingProgressLayoutBinding) = Unit

    fun show(binding: NodataLayoutBinding) = Unit

    fun isFavorite(): Boolean = false

    fun changeStatus(): DashboardUi = this

    fun id(): String = ""

    fun filmId(): Int = -1

    fun type(): DashboardUiType

    fun title(): String = ""

    object Progress : DashboardUi {

        override fun id(): String = javaClass.simpleName

        override fun type() = DashboardUiType.Progress

    }

    object PagingProgress: DashboardUi {

        override fun id(): String = javaClass.simpleName

        override fun type() = DashboardUiType.PagingProgress
    }

    object NoData: DashboardUi {
        override fun type() = DashboardUiType.NoData

    }

    data class Error(
        private val errorMessage: String,
    ) : DashboardUi {

        override fun id(): String = javaClass.simpleName + errorMessage

        override fun type() = DashboardUiType.Error

        override fun show(binding: DashboardErrorStateLayoutBinding) {
            binding.errorTextView.text = errorMessage
        }

    }

    data class Film(
        private val filmId: Int,
        private val overView: String,
        private val imageUrl: String,
        private val releaseDate: String,
        private val title: String,
        private val rate: Double,
        private val isFavorite: Boolean,
    ) : DashboardUi {

        override fun id(): String = filmId.toString()

        override fun filmId() = filmId

        override fun type() = DashboardUiType.Film

        override fun title() = title

        override fun show(binding: FilmItemLayoutBinding) = with(binding) {
            rateTextView.text = rate.toString()
            titleFilmTextView.text = title
            posterImageView.show(URL_POSTER + imageUrl)
            rateFilmBar.rating = setupRatBar(rate)
            iconImageView.setIcon(isFavorite = isFavorite)
        }

        override fun changeStatus() = copy(isFavorite = !isFavorite)

        override fun isFavorite() = isFavorite
    }

    companion object {

        private const val URL_POSTER = "https://image.tmdb.org/t/p/w500"

        private fun setupRatBar(rate: Double) = rate.toFloat() * 0.5F
    }

}