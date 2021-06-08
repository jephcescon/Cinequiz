package com.example.cinequiz.search.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.catalog.ErrorApi
import com.example.cinequiz.model.MovieCredits.MovieCredits
import com.example.cinequiz.model.MovieData.MovieData
import com.example.cinequiz.repository.MoviesRepository
import com.example.cinequiz.search.model.ItemSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val movies: MutableLiveData<MovieData> by lazy { MutableLiveData<MovieData>() }

    val creditsLiveData : MutableLiveData<MovieCredits> by lazy { MutableLiveData<MovieCredits>() }

    private val repository = MoviesRepository()

    init {
        moviesData()
        creditsList()
    }


    private fun moviesData() = CoroutineScope(Dispatchers.IO).launch {
        try {
            repository.getMovieData().let { moviesData ->
                movies.postValue(moviesData)
            }

        } catch (error: Throwable) {
            ErrorApi(error, errorMessage)
        }
    }


    fun creditsList() = CoroutineScope(Dispatchers.IO).launch {
        try {
            repository.getMovieCredits2().let {
                creditsLiveData.postValue(it)
                Dados.postActors(it.cast)
                Log.d("Dados", Dados.cast.toString())
            }
        }
        catch (error: Throwable) {
            ErrorApi(error, errorMessage)
        }
    }
}
