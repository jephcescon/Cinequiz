package com.example.cinequiz.model.quizModel


import com.google.gson.annotations.SerializedName

data class Quiz(
    @SerializedName("response_code")
    val responseCode: Int?,
    @SerializedName("results")
    val questions: List<Questions>?
)