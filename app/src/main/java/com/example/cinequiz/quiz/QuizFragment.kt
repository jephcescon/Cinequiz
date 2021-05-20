package com.example.cinequiz.quiz

import android.app.AlertDialog
import android.content.DialogInterface
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
    private val info by lazy { view?.findViewById<Button>(R.id.infoBtn) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPlay?.setOnClickListener {
            val intent = Intent(view.context, GameActivity::class.java)
            startActivity(intent)
        }

        info?.setOnClickListener {
            AlertDialog.Builder(view.context)
                .setTitle("Regras")
                .setMessage("1.Cada respota certa vale 10 pontos\n\n2.O tempo que você demora para responder não influencia na pontuação\n\n3.Somente é considerado ganhador se fizer mais de 60 pontos\n\n4.Cada pergunta só tem 1 alternativa correta")
                .setNegativeButton("OK", null)
                .create()
                .show()
        }
    }
}