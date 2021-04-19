package com.example.cinequiz.model.movieGenres


import com.google.gson.annotations.SerializedName

data class ListGenres(
    @SerializedName("genres")
    val genres: List<Genre>?
)