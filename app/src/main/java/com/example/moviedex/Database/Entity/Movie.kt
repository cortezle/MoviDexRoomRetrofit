package com.example.moviedex.Database.Entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "movies")
data class Movie (

    @PrimaryKey
    @field:Json(name="imdbID")
    val imdbID: String = "N/a",

    @field:Json(name="Title")
    val Title:String = "N/A",

    @field:Json(name="Year")
    val Year:String = "N/A",

    @field:Json(name="Released")
    val Released: String = "N/A",

    @field:Json(name="Runtime")
    val Runtime:String = "N/A",

    @field:Json(name="Genre")
    val Genre:String = "N/A",

    @field:Json(name="Director")
    val Director:String = "N/A",

    @field:Json(name="Actors")
    val Actors:String = "N/A",

    @field:Json(name="Plot")
    val Plot:String = "N/A",

    @field:Json(name="Language")
    val Language:String = "N/A",

    @field:Json(name="imdbRating")
    val imdbRating:String = "N/A",

    @field:Json(name="Poster")
    val Poster:String = "N/A"
)



data class MoviePreview(
    val Title: String = "N/A",
    val Year: String = "N/A",
    val imdbID: String = "N/A",
    val Type: String = "N/A",
    val Poster: String = "N/A",
    var selected: Boolean = false
)

data class OmbdMovieResponse (
    val Search: List<MoviePreview>,
    val totalResults: String,
    val Response: String
)