package com.example.cinequiz.model.popularMovieModel


import com.google.gson.annotations.SerializedName

data class PopularMovies(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val listMovies: List<PopularMoviesList>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)