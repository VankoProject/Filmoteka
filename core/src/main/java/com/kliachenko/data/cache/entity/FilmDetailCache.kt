package com.kliachenko.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kliachenko.data.mapper.FilmDetailMapper
import com.kliachenko.data.mapper.MapFilmDetail

@Entity(tableName = "detail_film_table")
data class FilmDetailCache(

    @PrimaryKey
    @ColumnInfo(name = "film_id")
    val filmId: Int,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "genres")
    val genres: List<String>,
    @ColumnInfo(name = "home_page")
    val homePage: String,
    @ColumnInfo(name = "production_countries")
    val productionCountries: List<String>,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "runtime")
    val runtime: Int,
    @ColumnInfo(name = "tagline")
    val tagline: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
) : MapFilmDetail {
    override fun <T : Any> map(mapper: FilmDetailMapper<T>): T {
        return mapper.map(
            filmId = filmId,
            adult = adult,
            genres = genres,
            homePage = homePage,
            productionCountries = productionCountries,
            originalLanguage = originalLanguage,
            title = title,
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            runtime = runtime,
            tagline = tagline,
            voteAverage = voteAverage,
            voteCount = voteCount,)
    }

}
