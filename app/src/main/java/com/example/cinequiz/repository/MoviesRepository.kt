package com.example.cinequiz.repository

import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.network.RetrofitMovieDB
import com.example.cinequiz.network.ServiceAPI


class MoviesRepository {
    companion object{
        const val name = "api_key"
        const val key = "4bb0d21f8adbe8563b3993393c720877"
    }

    private var url = "https://api.themoviedb.org/3/"
    private var service = ServiceAPI::class

    private val moviesService = RetrofitMovieDB(url).create(service)

    suspend fun getPopularMovies(page : Int = 1) = moviesService.getPopularMovies(page = page)

    suspend fun getDiscoverMovies(page: Int = 1, genre : String = "") = moviesService.getDiscoverMovies(page = page,withGenre = genre)

    suspend fun getMovieCredits() = moviesService.getActors(Dados.dados!!.id!!)
}