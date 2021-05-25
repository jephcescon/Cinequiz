package com.example.cinequiz.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.search.FireManagerMovie.FireManager

class AchievementsAdapter(val goalsList: MutableList<Achievement>) :
    RecyclerView.Adapter<AchievementsAdapter.AchievementsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.achievements_items, parent, false)
        return AchievementsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AchievementsViewHolder, position: Int) {
        holder.achievementText.text = goalsList[position].goal

    }

    override fun getItemCount(): Int = goalsList.size


    inner class AchievementsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val achievementText by lazy { itemView.findViewById<TextView>(R.id.achievements_text) }
    }

}