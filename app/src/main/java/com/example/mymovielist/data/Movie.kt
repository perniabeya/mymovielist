package com.example.mymovielist.data

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    val Search: List<Movie>
)

data class Movie(
    val Title: String,
    val Year:String,
    val Poster:String,
    val Plot:String,
    val Runtime: String,
    val Director: String,
    val Genre: String,
    val Country: String
)
