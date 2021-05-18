package com.example.cinequiz.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.model.popularMovieModel.PopularMoviesList
import com.example.cinequiz.search.model.ItemSearch
import com.squareup.picasso.Picasso

class ItemSearchAdapter :
        RecyclerView.Adapter<ItemSearchViewHolder>() {
    var itemSearchList = mutableListOf<PopularMoviesList>()


    override fun getItemCount(): Int = itemSearchList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSearchViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.card_search_item, parent, false)
        return ItemSearchViewHolder(view)
    }


    override fun onBindViewHolder(holder: ItemSearchViewHolder, position: Int) {
        val movieImage = holder.itemSearchMovieImg
        val movieName = holder.itemSearchMovieName
        val url = "https://image.tmdb.org/t/p/w500${itemSearchList[position].posterPath}"
        Picasso.get().load(url).into(movieImage)
        movieName.text = itemSearchList[position].title
        Log.d("Filme", itemSearchList[position].title!!)
    }

    fun addMovies(movies:PopularMoviesList){
        itemSearchList.add(movies)
        notifyDataSetChanged()
    }

}