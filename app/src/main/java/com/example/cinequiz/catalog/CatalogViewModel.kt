package com.example.cinequiz.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinequiz.model.popularMovieModel.PopularMovies
import com.example.cinequiz.model.popularMovieModel.PopularMoviesList
import com.example.cinequiz.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CatalogViewModel: ViewModel() {
    val movieLiveData: MutableLiveData<List<PopularMoviesList>> by lazy { MutableLiveData<List<PopularMoviesList>>() }
    private val moviesData = MovieSimulation

    private val repository = MoviesRepository()

    init {
        movieList()
    }

    private fun movieList() = CoroutineScope(IO).launch {
        repository.getPopularMovies().let {
            movieLiveData.postValue(it.results)
        }
    }
}