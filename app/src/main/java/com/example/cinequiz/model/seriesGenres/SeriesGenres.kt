package com.example.cinequiz.model.seriesGenres

import com.google.gson.annotations.SerializedName

data class SeriesGenres(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)
