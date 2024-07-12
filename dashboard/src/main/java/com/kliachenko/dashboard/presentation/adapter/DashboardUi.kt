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

    fun id(): String

    fun type(): DashboardUiType

    data class Progress(
        private val progressMessage: String,
    ) : DashboardUi {

        override fun id(): String = javaClass.simpleName

        override fun type() = DashboardUiType.Progress

        override fun show(binding: ProgressLayoutBinding) {
            binding.progressTextView.text = progressMessage
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
        private val filmId: Long,
        private val imageUrl: String,
        private val title: String,
        private val rate: Int,
        private val isFavorite: Boolean,
    ) : DashboardUi {

        override fun id(): String = filmId.toString()

        override fun type() = DashboardUiType.Film

        override fun show(binding: FilmItemLayoutBinding) = with(binding) {
            rateTextView.text = rate.toString()
            titleFilmTextView.text = title
            iconImageView.setImageResource(
                if (isFavorite)
                    R.drawable.ic_like_bookmark
                else
                    R.drawable.ic_unlike_bookmark
            )
            //todo add customView ImageView for using pic engine
        }
    }

}