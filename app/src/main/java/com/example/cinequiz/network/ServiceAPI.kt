package com.example.cinequiz.network

import com.example.cinequiz.model.popularMovieModel.PopularMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "pt_BR"
    ) : PopularMovies

}