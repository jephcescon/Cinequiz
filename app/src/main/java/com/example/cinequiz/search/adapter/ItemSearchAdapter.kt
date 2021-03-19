package com.example.cinequiz.search.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.MainActivity
import com.example.cinequiz.R
import com.example.cinequiz.search.SearchMenu
import com.example.cinequiz.search.model.ItemSearch

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
        movieImage.setBackgroundResource(itemSearchList[position].capaDoFilme)
        movieName.text = itemSearchList[position].nomeDoFilme


    }


}