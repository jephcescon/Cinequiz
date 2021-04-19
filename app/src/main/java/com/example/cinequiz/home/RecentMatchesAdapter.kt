package com.example.cinequiz.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R

class RecentMatchesAdapter(val matchesList: MutableList<Match>) : RecyclerView.Adapter<RecentMatchesAdapter.RecentMatchesViewHolder>() {

    val color: Int = Color.parseColor("#FF0000")
    val lose: String = "Perdeu"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMatchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_matches_items, parent,false)
        return RecentMatchesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentMatchesViewHolder, position: Int) {
        holder.matchResult.text = matchesList[position].result
        holder.matchDate.text = matchesList[position].date
        holder.matchImage.setImageResource(matchesList[position].image)
        if(matchesList[position].result.equals(lose)) {
            holder.matchColor.setBackgroundColor(color)
        }
    }

    override fun getItemCount(): Int = matchesList.size


    inner class RecentMatchesViewHolder(itemView : View) : RecyclerView.ViewHolder (itemView) {
        val matchResult by lazy {itemView.findViewById<TextView>(R.id.rec_matches_result)}
        val matchDate by lazy {itemView.findViewById<TextView>(R.id.rec_matches_date)}
        val matchImage by lazy {itemView.findViewById<ImageView>(R.id.rec_matches_icon)}
        val matchColor by lazy {itemView.findViewById<View>(R.id.rec_matches_color)}
    }

}