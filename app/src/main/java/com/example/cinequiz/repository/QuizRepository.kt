package com.example.cinequiz.repository

import com.example.cinequiz.network.RetrofitQuiz
import com.example.cinequiz.network.ServiceAPI

class QuizRepository {

    private var url = "https://opentdb.com/"
    private var service = ServiceAPI::class

    private val serviceRepository = RetrofitQuiz(url).create(service)

    suspend fun getQuizQuestions() = serviceRepository.getQuizQuestions()
}