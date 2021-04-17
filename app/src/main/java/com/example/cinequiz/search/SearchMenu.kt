package com.example.cinequiz.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.MainActivity
import com.example.cinequiz.R
import com.example.cinequiz.catalog.ImageRecycle
import com.example.cinequiz.search.adapter.ItemSearchAdapter
import com.example.cinequiz.search.model.ClickSearch
import com.example.cinequiz.search.model.ItemSearch

class SearchMenu : AppCompatActivity() {

    private val recycle by lazy { findViewById<RecyclerView>(R.id.rv_last_search) }
    private val btBackPress by lazy { findViewById<Button>(R.id.bt_return_search_movie) }
    private val searchField by lazy { findViewById<androidx.appcompat.widget.SearchView>(R.id.et_search_menu) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

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
        val movieSearchItens = mutableListOf<ItemSearch>()
        movieSearchItens.add(ItemSearch(R.drawable.filme, "Liga da injustiça"))
        movieSearchItens.add(ItemSearch(R.drawable.filme, "Os Vingativos"))
        movieSearchItens.add(ItemSearch(R.drawable.filme, "Os trapalhões"))
        movieSearchItens.add(ItemSearch(R.drawable.filme, "X-men"))
        return movieSearchItens

    }


}