package com.example.cinequiz.model.seriesGenres

import com.google.gson.annotations.SerializedName

data class SeriesListGenres(
    @SerializedName("genres")
    val seriesgenres: List<SeriesGenres>?
)
