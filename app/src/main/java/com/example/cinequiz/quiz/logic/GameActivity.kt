package com.example.cinequiz.quiz.logic

import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import com.example.cinequiz.R


class GameActivity : AppCompatActivity() {
    private lateinit var viewModel: GameViewModel
    private val question by lazy { findViewById<TextView>(R.id.question) }
    private val answer1 by lazy { findViewById<TextView>(R.id.answer1) }
    private val answer2 by lazy { findViewById<TextView>(R.id.answer2) }
    private val answer3 by lazy { findViewById<TextView>(R.id.answer3) }
    private val answer4 by lazy { findViewById<TextView>(R.id.answer4) }
    private val cardAnswer1 by lazy { findViewById<CardView>(R.id.cardAnswer1) }
    private val cardAnswer2 by lazy { findViewById<CardView>(R.id.cardAnswer2) }
    private val cardAnswer3 by lazy { findViewById<CardView>(R.id.cardAnswer3) }
    private val cardAnswer4 by lazy { findViewById<CardView>(R.id.cardAnswer4) }
    val barTimer: ProgressBar by lazy { findViewById(R.id.barTimer) }
    val textTimer: TextView by lazy { findViewById(R.id.textTimer) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        textObserve()

        textTimer.text = "Start"

        textTimer.setOnClickListener {
            if (textTimer.text == "Start"){
                cardAnswer1.visibility = VISIBLE
                cardAnswer2.visibility = VISIBLE
                cardAnswer3.visibility = VISIBLE
                cardAnswer4.visibility = VISIBLE
            }
            if (viewModel.questionActual != 10) {
                viewModel.ordination()
                setClickable(1)
                startTimer()
            }else{
                finish()
            }
        }
    }

    private fun textObserve() {
        viewModel.question.observe(this) {
            question.text = it
        }

        viewModel.answer1.observe(this){
            answer1.text = it
        }

        viewModel.answer2.observe(this){
            answer2.text = it
        }

        viewModel.answer3.observe(this){
            answer3.text = it
        }

        viewModel.answer4.observe(this){
            answer4.text = it
        }

    }

    private fun startTimer() {
        var complete = 100
        val countDownTimer = object : CountDownTimer((50 * 1000).toLong(), 1000) {
            // 500 means, onTick function will be called at every 500 milliseconds
            override fun onTick(leftTimeInMilliseconds: Long) {
                val seconds = leftTimeInMilliseconds / 1000
                complete-=2
                barTimer.progress = complete
                textTimer.text = String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60)
            }

            override fun onFinish() {
                if (textTimer.text.equals("00:00")) {
                    textTimer.text = "Next"
                    textTimer.isClickable = true
                    setClickable(0)
                } else {
                    textTimer.isClickable = false
                    textTimer.text = "0:50"
                    barTimer.progress = 100
                    setClickable(1)
                }
            }
        }.start()

        setClickAnswer(countDownTimer)
    }

    private fun configurationAnswerClick(countDownTimer: CountDownTimer) {
        barTimer.progress = 0
        textTimer.text = "Next"
        textTimer.isClickable = true
        countDownTimer.cancel()
    }

    private fun setClickAnswer(countDownTimer: CountDownTimer) {
        cardAnswer1.setOnClickListener {
            if (viewModel.answer1.value == viewModel.correctAnswer.value){
                Log.d("Acertou","Acertou")
            }
            configurationAnswerClick(countDownTimer)
            setClickable(0)
        }

        cardAnswer2.setOnClickListener {
            if (viewModel.answer2.value == viewModel.correctAnswer.value){
                Log.d("Acertou","Acertou")
            }
            configurationAnswerClick(countDownTimer)
            setClickable(0)
        }

        cardAnswer3.setOnClickListener {
            if (viewModel.answer3.value == viewModel.correctAnswer.value){
                Log.d("Acertou","Acertou")
            }
            configurationAnswerClick(countDownTimer)
            setClickable(0)
        }

        cardAnswer4.setOnClickListener {
            if (viewModel.answer4.value == viewModel.correctAnswer.value){
                Log.d("Acertou","Acertou")
            }
            configurationAnswerClick(countDownTimer)
            setClickable(0)
        }
    }

    private fun setClickable(i: Int) {
        if(i==0){
            cardAnswer1.isClickable = false
            cardAnswer2.isClickable = false
            cardAnswer3.isClickable = false
            cardAnswer4.isClickable = false
            answer1.setTextColor(getColor(R.color.disable))
            answer2.setTextColor(getColor(R.color.disable))
            answer3.setTextColor(getColor(R.color.disable))
            answer4.setTextColor(getColor(R.color.disable))
        }else{
            cardAnswer1.isClickable = true
            cardAnswer2.isClickable = true
            cardAnswer3.isClickable = true
            cardAnswer4.isClickable = true
            answer1.setTextColor(getColor(R.color.white))
            answer2.setTextColor(getColor(R.color.white))
            answer3.setTextColor(getColor(R.color.white))
            answer4.setTextColor(getColor(R.color.white))
        }
    }

}