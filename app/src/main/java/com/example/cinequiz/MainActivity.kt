package com.example.cinequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.home.RecentSearchesFragment
import com.example.cinequiz.search.FireManagerMovie.FireManager
import com.example.cinequiz.search.FireManagerMovie.MoviesFromFirebase
import com.example.cinequiz.search.activity.SearchMenu
import com.example.cinequiz.search.model.ItemSearch
import com.example.cinequiz.search.viewModel.SearchViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private var firestoreDb = Firebase.firestore
    private var firebaseAuth = FirebaseAuth.getInstance()
    val movieSearchItens = mutableListOf<ItemSearch>()
    val moviesList = movieSearchItens.asReversed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.navHostFragment)
        /*val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.quizFragment, R.id.catalogFragment, R.id.profileFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)*/
        bottomNavigationView.setupWithNavController(navController)
    }
}