package com.example.cinequiz.search.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.catalog.ErrorApi
import com.example.cinequiz.model.MovieCredits.MovieCredits
import com.example.cinequiz.model.MovieData.MovieData
import com.example.cinequiz.model.SeriesCredits.SeriesCredits
import com.example.cinequiz.model.tv.TvID
import com.example.cinequiz.repository.MoviesRepository
import com.example.cinequiz.search.model.ItemSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val movies: MutableLiveData<MovieData> by lazy { MutableLiveData<MovieData>() }
    val creditsLiveData : MutableLiveData<MovieCredits> by lazy { MutableLiveData<MovieCredits>() }
    val seriesCreditsLiveData : MutableLiveData<SeriesCredits> by lazy { MutableLiveData<SeriesCredits>() }
    val series: MutableLiveData<TvID> by lazy { MutableLiveData<TvID>() }

    private val repository = MoviesRepository()

    // SERIES ***********************

    fun seriesData() = CoroutineScope(Dispatchers.IO).launch {
        try {
            repository.getSeries().let { moviesData ->
                series.postValue(moviesData)
            }

        } catch (error: Throwable) {
            ErrorApi(error, errorMessage)
        }
    }


    fun creditsListTv() = viewModelScope.launch {
        try {
            repository.getSeriesActors().let {
                seriesCreditsLiveData.postValue(it)
            }
        }
        catch (error: Throwable) {
            ErrorApi(error, errorMessage)
        }
    }

    // MOVIES **************************

    fun moviesData() = CoroutineScope(Dispatchers.IO).launch {
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
