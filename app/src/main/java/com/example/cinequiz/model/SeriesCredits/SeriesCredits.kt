package com.example.cinequiz.model.SeriesCredits

data class SeriesCredits(
    val cast: List<SeriesCast>,
    val crew: List<SeriesCrew>,
    val id: Int
)