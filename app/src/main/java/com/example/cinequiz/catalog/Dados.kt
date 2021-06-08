package com.example.cinequiz.catalog

import com.example.cinequiz.model.MovieCredits.Cast
import com.example.cinequiz.search.fireManagerMovie.BuscasRecentes
import com.example.cinequiz.search.fireManagerMovie.MoviesFromFirebase

object Dados {
    var type: String? = null
    var dados: ImageRecycle? = null
    var cast : List<Cast>? = null
    var movieIDtoSearch = mutableListOf<BuscasRecentes>()
    var moviesFirebase = mutableListOf<BuscasRecentes>()




    fun postAll(infos:ImageRecycle){
        dados = infos
    }

    fun postActors(actors: List<Cast>){
        cast = actors
    }

    fun postMovieID(movieID : MoviesFromFirebase){
        movieIDtoSearch = movieID.buscasRecentes
    }
}