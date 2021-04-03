package com.example.cinequiz.network

import android.view.textclassifier.TextLanguage
import com.example.cinequiz.model.popularMovieModel.PopularMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "pt_BR",
        @Query("page") page: Int
    ) : PopularMovies

}