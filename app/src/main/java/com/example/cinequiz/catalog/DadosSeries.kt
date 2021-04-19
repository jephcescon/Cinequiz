package com.example.cinequiz.catalog


import com.example.cinequiz.model.SeriesCredits.SeriesCast

object DadosSeries {

    var dadosSeries: ImageRecycle? = null
    var seriesCast : List<SeriesCast>? = null

    fun postAll(infos:ImageRecycle){
        dadosSeries = infos
    }

    fun postActors(seriesActors: List<SeriesCast>){
        seriesCast = seriesActors
    }
}