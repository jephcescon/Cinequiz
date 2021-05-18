package com.example.cinequiz.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.cinequiz.R
import com.example.cinequiz.quiz.logic.GameActivity
import com.synnapps.carouselview.CarouselView

class QuizFragment : Fragment() {
    private val btnPlay by lazy { view?.findViewById<Button>(R.id.play) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPlay?.setOnClickListener {
            val intent= Intent(view.context, GameActivity::class.java)
            startActivity(intent)
        }
    }
}