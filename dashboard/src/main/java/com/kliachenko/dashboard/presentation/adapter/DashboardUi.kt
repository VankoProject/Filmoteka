package com.kliachenko.dashboard.presentation.adapter

import com.kliachenko.dashboard.R
import com.kliachenko.dashboard.databinding.ErrorLayoutBinding
import com.kliachenko.dashboard.databinding.FilmItemLayoutBinding
import com.kliachenko.dashboard.databinding.ProgressLayoutBinding

interface DashboardUi {

    fun show(binding: FilmItemLayoutBinding) = Unit

    fun show(binding: ErrorLayoutBinding) = Unit

    fun show(binding: ProgressLayoutBinding) = Unit

    fun isFavorite(): Boolean = false

    fun changeStatus(): DashboardUi = this

    fun id(): String

    fun type(): DashboardUiType

    object Progress : DashboardUi {

        override fun id(): String = javaClass.simpleName

        override fun type() = DashboardUiType.Progress

        override fun show(binding: ProgressLayoutBinding) {
            binding.progressTextView.text = R.string.loading_text.toString()
        }
    }

    data class Error(
        private val errorMessage: String,
    ) : DashboardUi {

        override fun id(): String = javaClass.simpleName + errorMessage

        override fun type() = DashboardUiType.Error

        override fun show(binding: ErrorLayoutBinding) {
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

        override fun type() = DashboardUiType.Film

        override fun show(binding: FilmItemLayoutBinding) = with(binding) {
            rateTextView.text = rate.toString()
            titleFilmTextView.text = title
            posterImageView.show(URL_POSTER + imageUrl)
            rateFilmBar.rating = setupRatBar(rate)
            iconImageView.setImageResource(
                if (isFavorite)
                    R.drawable.ic_like_bookmark
                else
                    R.drawable.ic_unlike_bookmark
            )
        }

        override fun changeStatus() = copy(isFavorite = !isFavorite)
    }

    companion object {
        private const val URL_POSTER = "https://image.tmdb.org/t/p/w500"

        fun setupRatBar(rate: Double) = rate.toFloat() * 0.5F
    }

}