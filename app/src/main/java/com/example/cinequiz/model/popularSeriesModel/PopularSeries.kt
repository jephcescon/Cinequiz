package com.example.cinequiz.model.popularSeriesModel


import com.google.gson.annotations.SerializedName

data class PopularSeries(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val listMovies: List<PopularSeriesList>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)