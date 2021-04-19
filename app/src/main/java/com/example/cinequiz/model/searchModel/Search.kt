package com.example.cinequiz.model.searchModel


import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val searchList: List<SearchResult>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)