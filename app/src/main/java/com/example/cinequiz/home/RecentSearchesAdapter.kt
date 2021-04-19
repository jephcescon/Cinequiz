package com.example.cinequiz.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R


class RecentSearchesAdapter (private val recSearchesList: MutableList<Movie>): RecyclerView.Adapter<RecentSearchesAdapter.RecentSearchesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_searches_items,parent,false)
        return RecentSearchesViewHolder(view)
    }

    override fun getItemCount(): Int = recSearchesList.size

    override fun onBindViewHolder(holder: RecentSearchesViewHolder, position: Int) {
        holder.banner.setImageResource(recSearchesList[position].imagem)
    }


    inner class RecentSearchesViewHolder(view: View):RecyclerView.ViewHolder(view){
        val banner by lazy { view.findViewById<ImageView>(R.id.bannerCatalog) }
    }
}