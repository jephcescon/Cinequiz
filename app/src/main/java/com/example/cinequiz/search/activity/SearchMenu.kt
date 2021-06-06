package com.example.cinequiz.search.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.search.fireManagerMovie.FireManager
import com.example.cinequiz.search.adapter.ItemSearchAdapter
import com.example.cinequiz.search.model.ClickSearch

class SearchMenu : AppCompatActivity() {

    private val recycle by lazy { findViewById<RecyclerView>(R.id.rv_last_search) }
    private val btBackPress by lazy { findViewById<Button>(R.id.bt_return_search_movie) }
    private val searchField by lazy { findViewById<androidx.appcompat.widget.SearchView>(R.id.et_search_menu) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        FireManager.getLastSearch()
        val moviesList = FireManager.getMovieSearchItem()



        recycle.layoutManager = LinearLayoutManager(this)

        val adapter = ItemSearchAdapter(moviesList)
        recycle.adapter = adapter
        recycle.adapter?.notifyDataSetChanged()

        btBackPress.setOnClickListener {
            super.onBackPressed()
        }

        searchField.setOnClickListener {
            ClickSearch.setValue()
            ClickSearch.textSearch = searchField.query.toString()
            finish()
            super.onBackPressed()

        }



    }



//     fun getMovieSearchItem(): MutableList<ItemSearch> {
//         val movieSearchItens = mutableListOf<ItemSearch>()
//         val moviesList = movieSearchItens.asReversed()
//         movieSearchItens.clear()
//         var position = 0
//         while (position < Dados.moviesFirebase.size){
//             movieSearchItens.add(ItemSearch(Dados.moviesFirebase[position].cover, Dados.moviesFirebase[position].title, Dados.moviesFirebase[position].cover))
//             position++
//         }
//
//         return moviesList
//
//    }


}