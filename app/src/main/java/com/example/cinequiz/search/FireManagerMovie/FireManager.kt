package com.example.cinequiz.search.FireManagerMovie

import android.util.Log
import com.example.cinequiz.catalog.Dados
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FireManager {


    private var firestoreDb = Firebase.firestore
    private var firebaseAuth = FirebaseAuth.getInstance()


    fun recordSearch(id: Int) {
        firebaseAuth.currentUser?.let { user ->

            firestoreDb.collection("lastSearch")
                .document(user.uid)
                .update("favoriteMovies",  FieldValue.arrayUnion(id))
                .addOnSuccessListener {
                    it
                }
                .addOnFailureListener {
                    it
                }
        }

    }

    fun getLastSearch() {

        firebaseAuth.currentUser?.let { user ->

            firestoreDb.collection("lastSearch")
                .document(user.uid)
                .get()
                .addOnSuccessListener {
                    val moviesIds = it.toObject(MovieIDs::class.java)
                    moviesIds?.favoriteMovies?.let { IDs -> Dados.postMoviesIDsFromFirebase(IDs) }
                    Log.d("IDs", Dados.moviesIDsFromFirebase.toString())

                }
                .addOnFailureListener {
                    it
                }
        }
    }

}