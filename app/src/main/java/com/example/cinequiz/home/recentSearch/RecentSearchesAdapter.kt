package com.example.cinequiz.home.recentSearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.search.fireManagerMovie.BuscasRecentes
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.catalog.details.MovieDetailsForSearch
import com.squareup.picasso.Picasso


class RecentSearchesAdapter (private val recSearchesList: MutableList<BuscasRecentes>): RecyclerView.Adapter<RecentSearchesAdapter.RecentSearchesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_searches_items,parent,false)
        return RecentSearchesViewHolder(view)
    }

    override fun getItemCount(): Int = recSearchesList.size

    override fun onBindViewHolder(holder: RecentSearchesViewHolder, position: Int) {
        val movieImage = holder.banner
        Picasso.get().load("https://image.tmdb.org/t/p/w500${recSearchesList[position].poster}").into(movieImage)

        holder.itemView.setOnClickListener {
            recSearchesList[position].id?.let { it1 -> Dados.sendIDToSearch(it1) }
            val intent = Intent(it.context, MovieDetailsForSearch::class.java)
            intent.putExtra("MOVIE",recSearchesList[position].type)
            intent.putExtra("ID",recSearchesList[position].id)
            it.context.startActivity(intent)
        }

    }


    inner class RecentSearchesViewHolder(view: View):RecyclerView.ViewHolder(view){
        val banner by lazy { view.findViewById<ImageView>(R.id.bannerCatalog) }
    }
}