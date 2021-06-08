package com.example.cinequiz.search.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.catalog.details.MovieDetailsForSearch
import com.example.cinequiz.search.model.ItemSearch
import com.squareup.picasso.Picasso

class ItemSearchAdapter(val itemSearchList: MutableList<ItemSearch>) :
    RecyclerView.Adapter<ItemSearchViewHolder>() {


    override fun getItemCount(): Int = itemSearchList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_search_item, parent, false)
        return ItemSearchViewHolder(view)
    }


    override fun onBindViewHolder(holder: ItemSearchViewHolder, position: Int) {
        val movieImage = holder.itemSearchMovieImg
        val movieName = holder.itemSearchMovieName


        movieName.text = itemSearchList[position].nomeDoFilme

        if (itemSearchList[position].nomeDoFilme == "Conteúdo indisponível") {
            Picasso.get().load(itemSearchList[position].imagePath).resize(135, 80).into(movieImage)
        } else Picasso.get()
            .load("https://image.tmdb.org/t/p/w500${itemSearchList[position].imagePath}")
            .resize(135, 80).into(movieImage)

        holder.fabItemSearch.setOnClickListener {
            Dados.sendIDToSearch(itemSearchList[position].movieID)
            val intent = Intent(it.context, MovieDetailsForSearch::class.java)
            intent.putExtra("ID", itemSearchList[position].movieID)
            intent.putExtra("MOVIE", itemSearchList[position].type)
            it.context.startActivity(intent)
        }


    }


}