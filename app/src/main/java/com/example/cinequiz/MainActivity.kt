package com.example.cinequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cinequiz.model.firestoreModels.StartFireBase
import com.example.cinequiz.profile.CallbackToControlLogin
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(), CallbackToControlLogin {
    private var fireStore = Firebase.firestore
    private var firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.navHostFragment)
        /*val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.quizFragment, R.id.catalogFragment, R.id.profileFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)*/
        bottomNavigationView.setupWithNavController(navController)

        startFirebase()
    }

    override fun logoutClick() {
        finish()
    }

    private fun startFirebase() {
        firebaseAuth.currentUser?.let { user ->
            fireStore.collection("users")
                .document(user.uid)
                .set(StartFireBase("Cinequiz"), SetOptions.merge())
        }
    }
}