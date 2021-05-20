package com.example.cinequiz.quiz.logic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinequiz.catalog.ErrorApi
import com.example.cinequiz.model.quizModel.Quiz
import com.example.cinequiz.repository.QuizRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel : ViewModel() {
    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val question by lazy { MutableLiveData<String>() }
    val correctAnswer by lazy { MutableLiveData<String>() }
    val answer1 by lazy { MutableLiveData<String>() }
    val answer2 by lazy { MutableLiveData<String>() }
    val answer3 by lazy { MutableLiveData<String>() }
    val answer4 by lazy { MutableLiveData<String>() }

    val quiz by lazy { MutableLiveData<Quiz>() }
    private val repository = QuizRepository()
    var questionActual = 0
    var score = 0

    init {
        questionsList()
    }

    private fun questionsList() = viewModelScope.launch {
        try {
            repository.getQuizQuestions().let {
                quiz.postValue(it)
            }
        } catch (error: Throwable) {
            ErrorApi(error, errorMessage)
        }
    }

    fun ordination(){
        quiz.value?.let {
            question.postValue(it.questions?.get(questionActual)?.question)
            correctAnswer.postValue(it.questions?.get(questionActual)?.correctAnswer)
            createOrdination(it.questions?.get(questionActual)?.correctAnswer,it.questions?.get(questionActual)?.incorrectAnswers)
            questionActual++
        }
    }

    private fun createOrdination(correct :String?, incorrect: List<String>?){
        val answers = mutableListOf<String>()
        val rand = Random.nextInt(0,4)
        var controller = 0


        for (i in 0 .. 3){
            if (i == rand) {
                correct?.let {
                    Log.d("teste", it)
                    answers.add(it) }

            }
            else {
                incorrect?.get(controller)?.let { answers.add(it) }
                controller++
            }
        }

        answer1.postValue(answers[0])
        answer2.postValue(answers[1])
        answer3.postValue(answers[2])
        answer4.postValue(answers[3])
    }
}