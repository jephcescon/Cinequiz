package com.example.cinequiz.home.recentSearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinequiz.search.fireManagerMovie.BuscasRecentes
import com.example.cinequiz.search.fireManagerMovie.MoviesFromFirebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecentSearchViewModel:ViewModel() {
    private var fireStore = Firebase.firestore
    private var firebaseAuth = Firebase.auth

    val searchListLiveData by lazy { MutableLiveData<MutableList<BuscasRecentes>>() }

    init {
        getLastSearch()
    }

    private fun getLastSearch(){
        firebaseAuth.currentUser?.let { user->
            fireStore.collection("users")
                .document(user.uid)
                .get()
                .addOnSuccessListener {
                    val searchList = it.toObject(MoviesFromFirebase::class.java)
                    searchListLiveData.postValue(searchList?.buscasRecentes?.asReversed())
                }
        }
    }
}