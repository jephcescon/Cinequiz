package com.example.cinequiz.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.search.FireManagerMovie.FireManager

class RecentSearchesFragment : Fragment() {


    private val searchRecycler by lazy { view?.findViewById<RecyclerView>(R.id.rec_searches_recycler)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FireManager.getLastSearch()


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_searches, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        FireManager.getLastSearch()
        val movieData = FireManager.getMovieSearchItem()

        searchRecycler?.layoutManager = GridLayoutManager(context,2)



        val adapter = RecentSearchesAdapter(movieData)


        searchRecycler?.adapter = adapter
        searchRecycler?.adapter?.notifyDataSetChanged()



    }


}