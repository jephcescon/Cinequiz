package com.example.cinequiz.model.discoverMovies


import com.google.gson.annotations.SerializedName

data class MoviesByGenre(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val listMoviesGenre: List<ListMoviesGenre>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)