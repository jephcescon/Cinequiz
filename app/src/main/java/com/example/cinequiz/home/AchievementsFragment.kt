package com.example.cinequiz.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R


class AchievementsFragment: Fragment() {

    private lateinit var achievementRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_achievements, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goalsList = mutableListOf<Achievement>()
        goalsList.add(Achievement("Completou a categoria: Terror!"))
        goalsList.add(Achievement("Completou a categoria: Aventura!"))
        goalsList.add(Achievement("Completou a categoria: Suspense!"))
        goalsList.add(Achievement("Completou a categoria: Drama!"))
        goalsList.add(Achievement("Completou a categoria: Ficção!"))
        goalsList.add(Achievement("Completou a categoria: Comédia!"))
        goalsList.add(Achievement("Alcançou 10 vitórias!"))
        goalsList.add(Achievement("Alcançou 9 vitória!"))
        goalsList.add(Achievement("Alcançou 8 vitória!"))
        goalsList.add(Achievement("Alcançou 7 vitórias!"))
        goalsList.add(Achievement("Alcançou 6 vitórias!"))
        goalsList.add(Achievement("Alcançou 5 vitórias!"))
        goalsList.add(Achievement("Alcançou 4 vitórias!"))
        goalsList.add(Achievement("Alcançou 3 vitórias!"))
        goalsList.add(Achievement("Alcançou 2 vitórias!"))
        goalsList.add(Achievement("Alcançou 1 vitória!"))


        achievementRecycler = view.findViewById(R.id.achievements_recycler)
        achievementRecycler.layoutManager = LinearLayoutManager(context)
        val adapter = AchievementsAdapter(goalsList)
        achievementRecycler.adapter = adapter
    }
}