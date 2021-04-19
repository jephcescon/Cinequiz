package com.example.cinequiz.model.discoverSeries


import com.google.gson.annotations.SerializedName

data class SeriesByGenre(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val listSeriesGenres: List<ListSeriesGenre>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)