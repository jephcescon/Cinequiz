package com.example.cinequiz.catalog

import com.example.cinequiz.R

object MovieSimulation {

    fun getMovie(): List<Movie>{
        val listMovies= mutableListOf<Movie>()
        for (i in 1..2) {
            listMovies.add(Movie("Filme $i Terror","Filme posição $i", R.drawable.deadpool,"Terror","Movie"))
            listMovies.add(Movie("Filme $i Romance","Filme posição $i", R.drawable.deadpool,"Romance","Movie"))
            listMovies.add(Movie("Filme $i Ficção","Filme posição $i", R.drawable.deadpool,"Ficção Científica","Movie"))
            listMovies.add(Movie("Filme $i Fantasia","Filme posição $i", R.drawable.deadpool,"Fantasia","Movie"))
            listMovies.add(Movie("Filme $i Suspence","Filme posição $i", R.drawable.deadpool,"Suspence","Movie"))
            listMovies.add(Movie("Serie $i Terror","Filme posição $i", R.drawable.deadpool,"Terror","Series"))
            listMovies.add(Movie("Serie $i Romance","Filme posição $i", R.drawable.deadpool,"Romance","Series"))
            listMovies.add(Movie("Serie $i Ficção","Filme posição $i", R.drawable.deadpool,"Ficção Científica","Series"))
            listMovies.add(Movie("Serie $i Fantasia","Filme posição $i", R.drawable.deadpool,"Fantasia","Series"))
            listMovies.add(Movie("Serie $i Suspence","Filme posição $i", R.drawable.deadpool,"Suspence","Series"))
        }
        return listMovies
    }
}
