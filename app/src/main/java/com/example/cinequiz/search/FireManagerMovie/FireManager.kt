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



    fun recordSearch(id: Int?, title : String, cover: String, poster: String) {
        firebaseAuth.currentUser?.let { user ->

            firestoreDb.collection("users")
                .document(user.uid)
//                .set(BuscasRecentes(id, title, cover, poster))
                .update("buscasRecentes", FieldValue.arrayUnion(BuscasRecentes(id, title, cover, poster)))
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
                    it
                    val moviesFromFirebase = it.toObject(MoviesFromFirebase::class.java)
                    Log.d("teste2", it.data.toString())
                    Log.d("teste2", moviesFromFirebase!!.buscasRecentes.toString())
                    Log.d("teste2", moviesFromFirebase!!.buscasRecentes[0].cover.toString())
                    Dados.moviesFirebase.clear()
                    Dados.moviesFirebase.addAll(moviesFromFirebase.buscasRecentes)

                    }
            .addOnFailureListener {
                it
            }
        }
    }}