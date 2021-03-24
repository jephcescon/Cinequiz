package com.example.cinequiz.search

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.search.adapter.ItemSearchAdapter
import com.example.cinequiz.search.model.ItemSearch

class SearchMenu : AppCompatActivity() {

    val recyle by lazy { findViewById<RecyclerView>(R.id.rv_last_search) }
    val btBackPress by lazy { findViewById<Button>(R.id.bt_return_search_movie) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val moviesList = getMovieSearchItem()

        recyle.layoutManager = LinearLayoutManager(this)
        val adapter = ItemSearchAdapter(moviesList)
        recyle.adapter = adapter



        btBackPress.setOnClickListener {
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