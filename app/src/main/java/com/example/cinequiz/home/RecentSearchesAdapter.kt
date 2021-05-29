package com.example.cinequiz.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.search.model.ItemSearch
import com.squareup.picasso.Picasso


class RecentSearchesAdapter (private val recSearchesList: MutableList<ItemSearch>): RecyclerView.Adapter<RecentSearchesAdapter.RecentSearchesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_searches_items,parent,false)
        return RecentSearchesViewHolder(view)
    }

    override fun getItemCount(): Int = recSearchesList.size

    override fun onBindViewHolder(holder: RecentSearchesViewHolder, position: Int) {
        val movieImage = holder.banner
        Picasso.get().load("https://image.tmdb.org/t/p/w500${Dados.moviesFirebase[position].poster}").into(movieImage)

    }


    inner class RecentSearchesViewHolder(view: View):RecyclerView.ViewHolder(view){
        val banner by lazy { view.findViewById<ImageView>(R.id.bannerCatalog) }
    }
}