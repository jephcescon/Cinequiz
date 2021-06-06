package com.example.cinequiz.home.recentSearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.search.fireManagerMovie.FireManager

class RecentSearchesFragment : Fragment() {
    private val searchRecycler by lazy { view?.findViewById<RecyclerView>(R.id.rec_searches_recycler)}

    private lateinit var viewModel: RecentSearchViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FireManager.getLastSearch()
        return inflater.inflate(R.layout.fragment_recent_searches, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RecentSearchViewModel::class.java)

//        FireManager.getLastSearch()
        viewModel.searchListLiveData.observe(viewLifecycleOwner){
            searchRecycler?.layoutManager = GridLayoutManager(context,2)
            val adapter = RecentSearchesAdapter(it)
            searchRecycler?.adapter = adapter
        }
    }


}