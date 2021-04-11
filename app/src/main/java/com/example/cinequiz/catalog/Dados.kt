package com.example.cinequiz.catalog

import com.example.cinequiz.model.MovieCredits.Cast

object Dados {
    var dados: ImageRecycle? = null
    var cast : List<Cast>? = null


    fun postAll(infos:ImageRecycle){
        dados = infos
    }

    fun postActors(actors: List<Cast>){
        cast = actors
    }


}