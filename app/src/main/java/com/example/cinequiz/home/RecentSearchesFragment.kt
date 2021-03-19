package com.example.cinequiz.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R

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

        val movieList = mutableListOf<Movie> ()
        movieList.add(Movie("Contra o Tempo", "Cinco alunos correm pra conseguir terminar o projeto PI a tempo", R.drawable.sourcecode,"Suspense","Filme"))
        movieList.add(Movie("Contra o Tempo", "Cinco alunos correm pra conseguir terminar o projeto PI a tempo", R.drawable.sourcecode,"Suspense","Filme"))
        movieList.add(Movie("Contra o Tempo", "Cinco alunos correm pra conseguir terminar o projeto PI a tempo", R.drawable.sourcecode,"Suspense","Filme"))
        movieList.add(Movie("Contra o Tempo", "Cinco alunos correm pra conseguir terminar o projeto PI a tempo", R.drawable.sourcecode,"Suspense","Filme"))
        movieList.add(Movie("Contra o Tempo", "Cinco alunos correm pra conseguir terminar o projeto PI a tempo", R.drawable.sourcecode,"Suspense","Filme"))
        movieList.add(Movie("Contra o Tempo", "Cinco alunos correm pra conseguir terminar o projeto PI a tempo", R.drawable.sourcecode,"Suspense","Filme"))
        movieList.add(Movie("Contra o Tempo", "Cinco alunos correm pra conseguir terminar o projeto PI a tempo", R.drawable.sourcecode,"Suspense","Filme"))
        movieList.add(Movie("Contra o Tempo", "Cinco alunos correm pra conseguir terminar o projeto PI a tempo", R.drawable.sourcecode,"Suspense","Filme"))
        movieList.add(Movie("Contra o Tempo", "Cinco alunos correm pra conseguir terminar o projeto PI a tempo", R.drawable.sourcecode,"Suspense","Filme"))
        movieList.add(Movie("Contra o Tempo", "Cinco alunos correm pra conseguir terminar o projeto PI a tempo", R.drawable.sourcecode,"Suspense","Filme"))

        searchRecycler = view.findViewById(R.id.rec_searches_recycler)
        searchRecycler.layoutManager = GridLayoutManager(context,2)
        val adapter = RecentSearchesAdapter(movieList)
        searchRecycler.adapter = adapter
    }
}