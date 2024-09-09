package com.kliachenko.detail.presentation

import android.view.View
import com.kliachenko.data.mapper.FilmDetailMapper
import com.kliachenko.detail.R
import com.kliachenko.detail.databinding.DetailSuccessfulStateLayoutBinding

interface FilmDetailUi {

    fun show(
        binding: DetailSuccessfulStateLayoutBinding,
        isFavorite: Boolean,
    )

    fun filmId(): Int = -1

    fun <T : Any> map(mapper: FilmDetailMapper<T>): T

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

        override fun <T : Any> map(mapper: FilmDetailMapper<T>): T {
            return mapper.map(
                filmId,
                adult,
                genres,
                homePage,
                productionCountries,
                originalLanguage,
                title,
                overview,
                posterPath,
                releaseDate,
                runtime,
                tagline,
                voteAverage,
                voteCount
            )
        }

        override fun show(
            binding: DetailSuccessfulStateLayoutBinding,
            isFavorite: Boolean,
        ) = with(binding) {
            filmNameTextView.text = title
            filmPosterView.show(URL_POSTER + posterPath)
            if (tagline.isNotEmpty()) taglineTextView.text =
                tagline else taglineTextView.visibility = View.GONE
            overviewFilmId.text = overview
            ageRatingTextViewId.ageRating(adult)
            originalLanguageTextView.originalLanguage(originalLanguage)
            homePageTextId.homePage(homePage)
            countryProductionTextView.countryProduction(productionCountries)
            likeCountTextViewId.text = voteCount.toString()
            scoreIconTextId.voteAverage(voteAverage)
            scoreProgressIconId.progress = (voteAverage * 10).toInt()
            favoriteIconBackgroundId.favoriteStatus(isFavorite)
            likeCountTextViewId.likesCount(voteCount)
            val iconRes =
                if (isFavorite) R.drawable.ic_favorite_bookmark else R.drawable.ic_unfavorite_bookmark
            favoriteIconId.setImageResource(iconRes)
            characteristicsTextView.characteristics(genres, releaseDate, runtime)
        }

    }

    companion object {

        private const val URL_POSTER = "https://image.tmdb.org/t/p/w500"

    }

}
