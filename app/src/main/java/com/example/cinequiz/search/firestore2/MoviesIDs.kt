package com.example.cinequiz.search.firestore2

class MoviesIDs() {
    var favoriteMovies : ArrayList<Int> = arrayListOf()

    constructor(favoriteMovies: ArrayList<Int>) : this(){
        this.favoriteMovies = favoriteMovies
    }
}