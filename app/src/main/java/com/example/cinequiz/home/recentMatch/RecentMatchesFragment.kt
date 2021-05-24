package com.example.cinequiz.home.recentMatch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.model.firestoreModels.LastGame


class RecentMatchesFragment : Fragment() {

    private lateinit var matchesRecycler : RecyclerView
    private lateinit var viewModel: RecentMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recent_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchesRecycler = view.findViewById(R.id.rec_matches_recycler)
        viewModel = ViewModelProviders.of(this).get()

        viewModel.getLastGames()
        val adapter = RecentMatchesAdapter()
        matchesRecycler.adapter = adapter

        matchesRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.lastResults.observe(viewLifecycleOwner){
            adapter.addList(it)
        }
    }
 }