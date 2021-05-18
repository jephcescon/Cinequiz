package com.example.cinequiz.search.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.search.adapter.ItemSearchAdapter
import com.example.cinequiz.search.firestore2.FireManager
import com.example.cinequiz.search.firestore2.MoviesIDs
import com.example.cinequiz.search.model.ClickSearch
import com.example.cinequiz.search.model.ItemSearch
import com.example.cinequiz.search.viewModel.SearchViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SearchMenu : AppCompatActivity() {

    private val recycle by lazy { findViewById<RecyclerView>(R.id.rv_last_search) }
    private val btBackPress by lazy { findViewById<Button>(R.id.bt_return_search_movie) }
    private val searchField by lazy { findViewById<androidx.appcompat.widget.SearchView>(R.id.et_search_menu) }
    private val searchViewModel by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        getLastSearch()
        recycle.layoutManager = LinearLayoutManager(this)
        val adapter = ItemSearchAdapter()
        recycle.adapter = adapter

        getMovieSearchItem(adapter)


        btBackPress.setOnClickListener {
            super.onBackPressed()
        }

        searchField.setOnClickListener {
            ClickSearch.setValue()
            ClickSearch.textSearch = searchField.query.toString()
            super.onBackPressed()
        }


    }




    private fun getMovieSearchItem(adapter: ItemSearchAdapter){
        searchViewModel.searchMovies(moviesIDsFirebase)
        searchViewModel.movies.observe(this){
            adapter.addMovies(it)
        }

    }

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


