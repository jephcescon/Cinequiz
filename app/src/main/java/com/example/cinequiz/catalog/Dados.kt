package com.example.cinequiz.catalog

import android.widget.ImageView
import com.example.cinequiz.model.MovieCredits.Cast
import com.example.cinequiz.search.model.ItemSearch

object Dados {
    var dados: ImageRecycle? = null
    var cast : List<Cast>? = null
    var movieIDtoSearch : Int = 1
    var moviesIDsFromFirebase = arrayListOf<Int>()
    var itemSearch = mutableListOf<ItemSearch>()




    fun postAll(infos:ImageRecycle){
        dados = infos
    }

    fun postActors(actors: List<Cast>){
        cast = actors
    }

    fun postMovieID(movieID : Int){
        movieIDtoSearch = movieID
    }

    fun postMoviesIDsFromFirebase(movieID : ArrayList<Int>){
        moviesIDsFromFirebase = movieID
    }

}