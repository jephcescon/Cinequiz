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

    private val serviceRepository = RetrofitMovieDB(url).create(service)

    suspend fun getPopularMovies(page : Int = 1) = serviceRepository.getPopularMovies(page = page)

    suspend fun getDiscoverMovies(page: Int = 1, genre : String = "") = serviceRepository.getDiscoverMovies(page = page,withGenre = genre)

    suspend fun getMovieCredits() = serviceRepository.getActors(Dados.dados!!.id!!)


//    suspend fun getMovieData() = serviceRepository.getMovieData(Dados.movieIDtoSearch!!)


    suspend fun search(page: Int = 1, search : String = "") = serviceRepository.search(page = page,search = search)

    suspend fun getPopularSeries(page : Int = 1) = serviceRepository.getPopularSeries(page = page)

    suspend fun getDiscoverSeries(page: Int = 1, genre : String = "") = serviceRepository.getDiscoverSeries(page = page,withGenre = genre)

}