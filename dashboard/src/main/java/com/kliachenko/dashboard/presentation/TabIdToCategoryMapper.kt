package com.kliachenko.dashboard.presentation

interface TabIdToCategoryMapper {

    fun map(tabPosition: Int): String

    object Base : TabIdToCategoryMapper {

        private val map = mapOf(
            POPULAR to "popular",
            TOP_RATED to "top_rated",
            UPCOMING to "upcoming"
        )

        override fun map(tabPosition: Int) =
            map[tabPosition] ?: throw IllegalStateException("Unknown tabPosition $tabPosition")

    }

    companion object {
        private const val POPULAR = 0
        private const val TOP_RATED = 1
        private const val UPCOMING = 2
    }
}