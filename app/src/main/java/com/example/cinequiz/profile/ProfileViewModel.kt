package com.example.cinequiz.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileViewModel:ViewModel() {
    val name by lazy { MutableLiveData<String>() }
    val photo by lazy { MutableLiveData<Uri>() }

    private val firebaseAuth = Firebase.auth
    private var fireStore = Firebase.firestore

    init {
        configUserInfo()
    }

    fun logoff(googleLogin: GoogleSignInClient){
        googleLogin.signOut()
        firebaseAuth.signOut()
    }

    fun deleteAccount(googleLogin: GoogleSignInClient){
        val user = firebaseAuth.currentUser
        user?.let {
            fireStore.collection("users")
                .document(user.uid)
                .delete()
        }
        user?.delete()
        googleLogin.signOut()
    }

    private fun configUserInfo(){
        val user = firebaseAuth.currentUser
        user?.let {
            name.postValue(it.displayName)
            photo.postValue(it.photoUrl)
        }
    }
}