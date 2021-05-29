package com.example.cinequiz.search.FireManagerMovie

import android.util.Log
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.search.model.ItemSearch
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FireManager {


    private var firestoreDb = Firebase.firestore
    private var firebaseAuth = FirebaseAuth.getInstance()


    fun recordSearch(id: Int?, title: String, cover: String, poster: String) {
        firebaseAuth.currentUser?.let { user ->

            firestoreDb.collection("users")
                .document(user.uid)
//                .set(BuscasRecentes(id, title, cover, poster))
                .update(
                    "buscasRecentes",
                    FieldValue.arrayUnion(BuscasRecentes(id, title, cover, poster))
                )
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

            firestoreDb.collection("users")
                .document(user.uid)
                .get()
                .addOnSuccessListener {
                    val moviesFromFirebase = it.toObject(MoviesFromFirebase::class.java)
                    Dados.moviesFirebase.clear()
                    moviesFromFirebase?.let { it1 -> Dados.moviesFirebase.addAll(it1.buscasRecentes) }

                }
                .addOnFailureListener {
                    Log.d("erro", it.message.toString())
                }
        }
    }

    

}