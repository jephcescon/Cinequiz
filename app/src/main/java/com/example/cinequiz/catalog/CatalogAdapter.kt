package com.example.cinequiz.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.google.android.material.snackbar.Snackbar

class CatalogAdapter(private val bannerList:List<Movie>, val callback :(Int)->Unit): RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_catalog,parent,false)
        return CatalogViewHolder(view)
    }

    override fun getItemCount(): Int = bannerList.size

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.cardBanner.setOnClickListener{
            callback(position)
        }
    }

    inner class CatalogViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cardBanner: CardView by lazy { view.findViewById<CardView>(R.id.cardBanner) }
        val banner by lazy { view.findViewById<ImageView>(R.id.bannerCatalog) }
    }
}