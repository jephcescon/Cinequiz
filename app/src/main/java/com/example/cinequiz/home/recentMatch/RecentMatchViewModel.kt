package com.example.cinequiz.home.recentMatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinequiz.model.firestoreModels.LastGame
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecentMatchViewModel:ViewModel() {
    private var fireStore = Firebase.firestore
    private var firebaseAuth = Firebase.auth

    val lastResults by lazy { MutableLiveData<LastGame>() }


    fun getLastGames(){
        firebaseAuth.currentUser?.let { user->
            fireStore.collection("users")
                .document(user.uid)
                .collection("lastGame")
                .document("lastGame")
                .get()
                .addOnSuccessListener {
                    val lastGameObject = it.toObject(LastGame::class.java)
                    lastResults.postValue(lastGameObject)
                }
        }
    }
}