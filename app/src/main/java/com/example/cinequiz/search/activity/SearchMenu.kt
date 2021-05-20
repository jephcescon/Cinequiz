package com.example.cinequiz.search.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.model.MovieCredits.Cast
import com.example.cinequiz.search.FireManagerMovie.FireManager
import com.example.cinequiz.search.adapter.ItemSearchAdapter
import com.example.cinequiz.search.model.ClickSearch
import com.example.cinequiz.search.model.ItemSearch
import com.example.cinequiz.search.viewModel.SearchViewModel

class SearchMenu : AppCompatActivity() {

    private val recycle by lazy { findViewById<RecyclerView>(R.id.rv_last_search) }
    private val btBackPress by lazy { findViewById<Button>(R.id.bt_return_search_movie) }
    private val searchField by lazy { findViewById<androidx.appcompat.widget.SearchView>(R.id.et_search_menu) }
    private val searchViewModel by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }
    var result = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        FireManager.getLastSearch()
        Log.d("IDs2", Dados.moviesIDsFromFirebase.toString())
        val moviesList = getMovieSearchItem()

        recycle.layoutManager = LinearLayoutManager(this)
        val adapter = ItemSearchAdapter(moviesList)
        recycle.adapter = adapter

        btBackPress.setOnClickListener {
            super.onBackPressed()
        }

        searchField.setOnClickListener {
            ClickSearch.setValue()
            ClickSearch.textSearch = searchField.query.toString()
            super.onBackPressed()
        }



    }


    private fun getMovieSearchItem(): MutableList<ItemSearch> {
        searchViewModel.movies.observe(this){

            Dados.itemSearch
            Log.d("IDs3", Dados.itemSearch.size.toString())
        }
        return Dados.itemSearch

    }


}