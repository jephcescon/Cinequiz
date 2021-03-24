package com.example.cinequiz.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CatalogViewModel: ViewModel() {
    val movieLiveData: MutableLiveData<List<Movie>> by lazy { MutableLiveData<List<Movie>>() }
    private val moviesData = MovieSimulation

    init {
        movieList()
    }

    private fun movieList(){
        val list = moviesData.getMovie()
        movieLiveData.postValue(list)
    }
}