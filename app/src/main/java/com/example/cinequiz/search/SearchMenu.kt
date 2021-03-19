package com.example.cinequiz.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.search.adapter.ItemSearchAdapter
import com.example.cinequiz.search.model.ItemSearch
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SearchMenu : AppCompatActivity() {

    val recyle by lazy { findViewById<RecyclerView>(R.id.rv_last_search) }
    val fbBackPress by lazy { findViewById<FloatingActionButton>(R.id.fb_back_searchmenu) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val moviesList = getMovieSearchItem()

        recyle.layoutManager = LinearLayoutManager(this)
        val adapter = ItemSearchAdapter(moviesList)
        recyle.adapter = adapter

        // ****COLOCANDO ACTIONBAR NO PROJETO****
        val action = supportActionBar!!
        action.setDisplayShowCustomEnabled(true)
        action.setCustomView(R.layout.actionbar_search)


        fbBackPress.setOnClickListener {
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