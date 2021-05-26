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
import com.example.cinequiz.search.FireManagerMovie.FireManager
import com.example.cinequiz.search.viewModel.SearchViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    val searchViewModelInit by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewModelInit.movies.observe(this){
            Dados.itemSearch
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.navHostFragment)
        /*val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.quizFragment, R.id.catalogFragment, R.id.profileFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)*/
        bottomNavigationView.setupWithNavController(navController)
    }
}