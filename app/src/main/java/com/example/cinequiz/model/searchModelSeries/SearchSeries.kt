package com.example.cinequiz.model.searchModelSeries

import com.google.gson.annotations.SerializedName


data class SearchSeries(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val searchList: List<SearchSeriesResult>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)
