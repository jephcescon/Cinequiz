package com.example.cinequiz.search.firestore2

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireManager {

    var moviesIDsFirebase = arrayListOf<Int>()

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
//        Log.d("Retorno", FavoriteMovies().oi.size.toString())
    }

    fun getLastSearch() {

        firebaseAuth.currentUser?.let { user ->

            firestoreDb.collection("lastSearch")
                .document(user.uid)
                .get()
                .addOnSuccessListener {
                    val moviesIds = it.toObject(MoviesIDs::class.java)
                    moviesIds?.favoriteMovies?.let { IDs -> moviesIDsFirebase.addAll(IDs) }
                    Log.d("IDs", moviesIDsFirebase.toString())

                }
                .addOnFailureListener {
                    it
                }
        }
    }

}