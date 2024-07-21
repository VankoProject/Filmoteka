package com.kliachenko.domain

interface DashboardItem {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapItems(
            id: Int,
            overview: String,
            posterPath: String,
            releaseDate: String,
            title: String,
            voteAverage: Double,
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
    override fun <T : Any> map(mapper: DashboardItem.Mapper<T>): T =
        mapper.mapItems(id, overview, posterPath, releaseDate, title, voteAverage)
}
