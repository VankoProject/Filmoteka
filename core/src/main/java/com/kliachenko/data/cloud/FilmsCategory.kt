package com.kliachenko.data.cloud

interface FilmsCategory {

    val categoryName: String

    object Popular: FilmsCategory {
        override val categoryName = POPULAR
    }

    object TopRated: FilmsCategory {
        override val categoryName = TOP_RATED
    }

    object Upcoming: FilmsCategory {
        override val categoryName = UPCOMING
    }

    companion object {
        private const val POPULAR = "popular"
        private const val TOP_RATED = "top_rated"
        private const val UPCOMING = "upcoming"
    }
}