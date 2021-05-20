package com.example.cinequiz.quiz.logic

import androidx.lifecycle.ViewModel
import com.example.cinequiz.model.firestoreModels.LastGame
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ResultViewModel : ViewModel() {
    private var fireStore = Firebase.firestore
    private var firebaseAuth = Firebase.auth

    private fun addScoreLastGame(lastGamesObject: LastGame?) {
        firebaseAuth.currentUser?.let { user ->
            lastGamesObject?.let {
                fireStore.collection("users")
                    .document(user.uid)
                    .collection("lastGame")
                    .document("lastGame")
                    .set(it)
            }
        }
    }

    fun scoreLastGame(score: Int) {
        firebaseAuth.currentUser?.let { user ->
            fireStore.collection("users")
                .document(user.uid)
                .collection("lastGame")
                .document("lastGame")
                .get()
                .addOnSuccessListener {
                    val lastGamesObject = it.toObject(LastGame::class.java)
                    addScoreLastGame(organizeList(lastGamesObject, score))
                }
        }
    }

    private fun organizeList(lastGamesObject: LastGame?, score: Int): LastGame {
        val lastGames = lastGamesObject ?: LastGame(mutableListOf())

        if (lastGames.lastGame.size < 10) {
            lastGames.lastGame.add(0,score)
        } else {
            lastGames.lastGame.remove(lastGames.lastGame.last())
            lastGames.lastGame.add(0,score)
        }

        return lastGames
    }
}