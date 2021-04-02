package com.example.cinequiz.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.model.popularMovieModel.PopularMoviesList
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class CatalogAdapter(private val bannerList:List<PopularMoviesList>, val callback :(Int)->Unit): RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_catalog,parent,false)
        return CatalogViewHolder(view)
    }

    override fun getItemCount(): Int = bannerList.size

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val imagePath = bannerList[position]
        holder.cardBanner.setOnClickListener{
            callback(position)
        }

        val url = "https://image.tmdb.org/t/p/original${imagePath.posterPath}"
        Picasso.get().load(url).into(holder.banner)
    }

    inner class CatalogViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cardBanner: CardView by lazy { view.findViewById<CardView>(R.id.cardBanner) }
        val banner: ImageView by lazy { view.findViewById<ImageView>(R.id.bannerCatalog) }
    }
}