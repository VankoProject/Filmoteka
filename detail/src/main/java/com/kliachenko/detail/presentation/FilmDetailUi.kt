package com.kliachenko.detail.presentation

import com.kliachenko.detail.databinding.DetailErrorStateLayoutBinding
import com.kliachenko.detail.databinding.DetailSuccessfulStateLayoutBinding

interface FilmDetailUi {

    fun show(binding: DetailErrorStateLayoutBinding) = Unit

    fun show(binding: DetailSuccessfulStateLayoutBinding) = Unit

    fun filmId(): Int = -1

    object Progress : FilmDetailUi

    data class Error(private val message: String) : FilmDetailUi {
        override fun show(binding: DetailErrorStateLayoutBinding) {
            binding.errorDetailTextView.text = message
        }
    }

    data class FilmDetail(
        private val filmId: Int,
        private val adult: Boolean,
        private val genres: List<String>,
        private val homePage: String,
        private val productionCountries: List<String>,
        private val originalLanguage: String,
        private val title: String,
        private val overview: String,
        private val posterPath: String,
        private val releaseDate: String,
        private val runtime: Int,
        private val tagline: String,
        private val voteAverage: Double,
        private val voteCount: Int,
    ) : FilmDetailUi {
        override fun filmId() = filmId

        override fun show(binding: DetailSuccessfulStateLayoutBinding) {
            binding.filmNameTextView.text = title
            binding.filmPosterView.show(posterPath)
            binding.taglineTextView.text = tagline
            binding.overviewFilmId.text = overview
            binding.originalLanguageTextView.text = originalLanguage
            binding.homePageTextId.text = homePage
            binding.countryProductionTextView.text = productionCountries.toString()
            binding.likeCountTextViewId.text = voteCount.toString()
            binding.scoreIconTextId.text = voteAverage.toString()
        }
    }

}
