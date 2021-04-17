package com.example.cinequiz.network

import com.example.cinequiz.model.MovieCredits.MovieCredits
import com.example.cinequiz.model.discoverMovies.MoviesByGenre
import com.example.cinequiz.model.movieGenres.ListGenres
import com.example.cinequiz.model.popularMovieModel.PopularMovies
import com.example.cinequiz.model.searchModel.Search
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int
    ) : PopularMovies

    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("language") language: String = "pt-BR",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("with_genres") withGenre: String
    ): MoviesByGenre


    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieID : Int,
        @Query("language") language: String = "pt-BR"
    ) : MovieCredits

    @GET("search/multi")
    suspend fun search(
        @Query("language") language: String = "pt-BR",
        @Query("query") search:String,
        @Query("page") page: Int
    ) : Search
}