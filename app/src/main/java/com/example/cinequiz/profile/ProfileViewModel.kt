package com.example.cinequiz.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ProfileViewModel : ViewModel() {
    val name by lazy { MutableLiveData<String>() }
    val photo by lazy { MutableLiveData<Uri>() }

    private val firebaseAuth = Firebase.auth
    private var fireStore = Firebase.firestore
    private val firebaseStorage = Firebase.storage

    init {
        configUserInfo()
    }

    fun logoff(googleLogin: GoogleSignInClient) {
        googleLogin.signOut()
        firebaseAuth.signOut()
    }

    fun deleteAccount(googleLogin: GoogleSignInClient) {
        val user = firebaseAuth.currentUser
        user?.let {
            fireStore.collection("users")
                .document(user.uid)
                .delete()

            firebaseStorage.getReference("uploads")
                .child(user.uid)
                .delete()
        }
        user?.delete()
        googleLogin.signOut()
    }

    private fun configUserInfo() {
        val user = firebaseAuth.currentUser
        user?.let { userVerification->
            name.postValue(userVerification.displayName)

            firebaseStorage.getReference("uploads")
                .child(user.uid)
                .downloadUrl
                .addOnSuccessListener { uri ->
                    photo.postValue(uri)
                }.addOnFailureListener {
                    photo.postValue(userVerification.photoUrl)
                }

        }
    }

    fun changeProfileImage(image: Uri?) {
        val user = firebaseAuth.currentUser
        image?.let { imageUri ->
            user?.let { user ->
                firebaseStorage.getReference("uploads")
                    .child(user.uid)
                    .putFile(imageUri)
                    .addOnSuccessListener {
                        Log.d("work", "work")
                    }.addOnFailureListener {
                        Log.d("work", "nwork")
                    }.addOnProgressListener {
                        it
                    }
            }
        }
    }
}