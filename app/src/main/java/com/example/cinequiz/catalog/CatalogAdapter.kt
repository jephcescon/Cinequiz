package com.example.cinequiz.catalog

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.catalog.details.MovieDetails
import com.example.cinequiz.model.MovieCredits.Cast
import com.squareup.picasso.Picasso

class CatalogAdapter(val callback :(ImageRecycle)->Unit): RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>(){
    var imagens= mutableListOf<ImageRecycle>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_catalog,parent,false)
        return CatalogViewHolder(view)
    }

    override fun getItemCount(): Int = imagens.size

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val imagePath = imagens[position]
        holder.cardBanner.setOnClickListener{
            callback(imagePath)
        }

        val url = "https://image.tmdb.org/t/p/w500${imagePath.banner}"
        Picasso.get().load(url).into(holder.banner)

        holder.banner.setOnClickListener {
            val intent = Intent(it.context, MovieDetails::class.java)
            Dados.postAll(imagePath)
            it.context.startActivity(intent)
            Log.d("dados20", imagePath.synopses.toString())
            Log.d("dados21", imagePath.title.toString())
        }

    }

    inner class CatalogViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cardBanner: CardView by lazy { view.findViewById<CardView>(R.id.cardBanner) }
        val banner: ImageView by lazy { view.findViewById<ImageView>(R.id.bannerCatalog) }
    }

    fun addMovies(movies : List<ImageRecycle>){
        imagens.addAll(movies)
        notifyDataSetChanged()
    }


    fun resetRecycle(){
        imagens.clear()
        notifyDataSetChanged()
    }
}