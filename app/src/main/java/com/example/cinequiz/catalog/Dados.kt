package com.example.cinequiz.catalog

import android.graphics.Movie
import android.util.Log
import com.example.cinequiz.model.MovieCredits.Cast
import com.example.cinequiz.model.MovieData.MovieData
import com.example.cinequiz.search.fireManagerMovie.BuscasRecentes
import com.example.cinequiz.search.fireManagerMovie.MoviesFromFirebase
import com.example.cinequiz.search.viewModel.SearchViewModel

object Dados {
    var type: String? = null
    var dados: ImageRecycle? = null
    var cast: List<Cast>? = null
    var movieIDtoSearch = mutableListOf<BuscasRecentes>()
    var moviesFirebase = mutableListOf<BuscasRecentes>()
    var movieID = 1

    fun postAll(infos:ImageRecycle){
        dados = infos
    }

    fun postActors(actors: List<Cast>){
        cast = actors
    }

    fun postMovieID(movieID : MoviesFromFirebase){
        movieIDtoSearch = movieID.buscasRecentes
    }

    fun sendIDToSearch(ID: Int) {
        movieID = ID
//        SearchViewModel().movies
//        Log.d("viewModel", SearchViewModel().movies.value.toString())
        Log.d("viewModel", movieID.toString())


    }


}