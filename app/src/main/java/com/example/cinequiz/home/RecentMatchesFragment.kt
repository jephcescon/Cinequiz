package com.example.cinequiz.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R


class RecentMatchesFragment : Fragment() {

    private lateinit var matchesRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val matchesList = mutableListOf<Match> ()
        matchesList.add(Match("Perdeu", "02/02", R.drawable.ic_thumb_down))
        matchesList.add(Match("Ganhou!", "03/03", R.drawable.ic_thumb_up))
        matchesList.add(Match("Ganhou!", "05/05", R.drawable.ic_thumb_up))
        matchesList.add(Match("Perdeu", "05/05", R.drawable.ic_thumb_down))
        matchesList.add(Match("Ganhou!", "01/01", R.drawable.ic_thumb_up))
        matchesList.add(Match("Perdeu", "04/04", R.drawable.ic_thumb_down))
        matchesList.add(Match("Perdeu", "02/02", R.drawable.ic_thumb_down))
        matchesList.add(Match("Ganhou!", "03/03", R.drawable.ic_thumb_up))
        matchesList.add(Match("Perdeu", "04/04", R.drawable.ic_thumb_down))
        matchesList.add(Match("Ganhou!", "05/05", R.drawable.ic_thumb_up))
        matchesList.add(Match("Perdeu", "05/05", R.drawable.ic_thumb_down))

        matchesRecycler = view.findViewById(R.id.rec_matches_recycler)
        matchesRecycler.layoutManager = LinearLayoutManager(context)
        val adapter = RecentMatchesAdapter(matchesList)
        matchesRecycler.adapter = adapter
    }
 }