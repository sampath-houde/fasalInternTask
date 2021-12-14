package com.example.fasakinternshiptask.model

data class MovieSearchResponse(
    val Response: String,
    val Search: List<Movie>?,
    val totalResults: String
)

data class Movie(
    val Poster: String?,
    val Title: String?,
    val Type: String?,
    val Year: String?,
    val imdbID: String?
)