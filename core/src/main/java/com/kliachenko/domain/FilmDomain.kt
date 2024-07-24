package com.kliachenko.domain

interface DashboardItem {

    fun <T : Any> map(mapper: Mapper<T>, isFavorite: Boolean): T

    interface Mapper<T : Any> {
        fun mapItems(
            id: Int,
            overview: String,
            posterPath: String,
            releaseDate: String,
            title: String,
            voteAverage: Double,
            isFavorite: Boolean,
        ): T
    }
}

data class FilmDomain(
    private val id: Int,
    private val overview: String,
    private val posterPath: String,
    private val releaseDate: String,
    private val title: String,
    private val voteAverage: Double,
) : DashboardItem {

    fun id(): Int = id

    override fun <T : Any> map(mapper: DashboardItem.Mapper<T>, isFavorite: Boolean): T =
        mapper.mapItems(
            id,
            overview,
            posterPath,
            releaseDate,
            title,
            voteAverage,
            isFavorite
        )
}
