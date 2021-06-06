package com.example.cinequiz.search.fireManagerMovie

import android.util.Log
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.search.model.ItemSearch
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FireManager {


    private var firestoreDb = Firebase.firestore
    private var firebaseAuth = FirebaseAuth.getInstance()


    fun recordSearch(id: Int?, title: String?, cover: String?, poster: String?) {
        firebaseAuth.currentUser?.let { user ->

            firestoreDb.collection("users")
                .document(user.uid)
//                .set(BuscasRecentes(id, title!!, cover!!, poster!!), SetOptions.merge())
                .update(
                    "buscasRecentes",
                    FieldValue.arrayUnion(BuscasRecentes(id, title, cover, poster))
                )
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
                    Log.d("erroDois", it.message.toString())
                }
        }
    }

    fun getMovieSearchItem(): MutableList<ItemSearch> {
        val movieSearchItens = mutableListOf<ItemSearch>()
        val moviesList = movieSearchItens.asReversed()
//        movieSearchItens.clear()
        var position = 0
        while (position < Dados.moviesFirebase.size) {
            movieSearchItens.add(ItemSearch(Dados.moviesFirebase[position].cover, Dados.moviesFirebase[position].title, Dados.moviesFirebase[position].cover))
            position++
        }

        Log.d("firemanager", moviesList.size.toString())
        return moviesList


    }
}