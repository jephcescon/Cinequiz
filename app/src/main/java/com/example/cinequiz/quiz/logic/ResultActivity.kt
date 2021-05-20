package com.example.cinequiz.quiz.logic

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import com.example.cinequiz.MainActivity
import com.example.cinequiz.R

class ResultActivity : AppCompatActivity() {
    val resultImage by lazy { findViewById<ImageView>(R.id.resultImage) }
    private val resultText by lazy { findViewById<TextView>(R.id.resultText) }
    private val scoreCard by lazy { findViewById<CardView>(R.id.scoreCard) }
    private val scorePoint by lazy { findViewById<TextView>(R.id.scoreValue) }

    lateinit var viewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        viewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)

        val getPoint = intent.extras?.getInt("SCORE")

        if (getPoint != null){
            scorePoint.text = getPoint.toString()
            viewModel.scoreLastGame(getPoint)
            validateScore(getPoint)
        }else
            Toast.makeText(this,"Ocorreu algum erro",Toast.LENGTH_LONG).show()

    }

    private fun validateScore(point: Int) {
        if (point < 60){
            resultText.text = "Tente Novamente!"
            scoreCard
            scoreCard.backgroundTintList = ColorStateList.valueOf(getColor(R.color.loser))
        }else{
            resultText.text = "Parabéns!"
            scoreCard.backgroundTintList = ColorStateList.valueOf(getColor(R.color.winner))
        }
    }

    fun playAgain(view:View){
        val intent = Intent(this,GameActivity::class.java)
        finish()
        startActivity(intent)
    }

    fun mainScreen(view:View){
        val intent = Intent(this,MainActivity::class.java)
        finish()
        startActivity(intent)
    }
}