package com.example.cinequiz.search.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R

class ItemSearchViewHolder(view: View)  : RecyclerView.ViewHolder(view) {

    val itemSearchMovieName by lazy { itemView.findViewById<TextView>(R.id.tv_last_search_item) }
    val itemSearchMovieImg by lazy { itemView.findViewById<ImageView>(R.id.iv_img_item_search) }


}