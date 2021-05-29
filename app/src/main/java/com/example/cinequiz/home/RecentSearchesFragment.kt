package com.example.cinequiz.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.MainActivity
import com.example.cinequiz.R
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.search.FireManagerMovie.FireManager
import com.example.cinequiz.search.activity.SearchMenu
import com.example.cinequiz.search.model.ItemSearch
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class RecentSearchesFragment : Fragment() {

    private lateinit var searchRecycler: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_searches, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchRecycler = view.findViewById(R.id.rec_searches_recycler)
        searchRecycler.layoutManager = GridLayoutManager(context,2)
        FireManager.getLastSearch()


        val movieData = SearchMenu().getMovieSearchItem()



        val adapter = RecentSearchesAdapter(movieData)
        searchRecycler.adapter = adapter
        searchRecycler.adapter?.notifyDataSetChanged()



    }


}