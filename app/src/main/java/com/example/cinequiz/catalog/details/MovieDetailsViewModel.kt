package com.example.cinequiz.catalog.details

import android.util.Log
import androidx.lifecycle.*
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.catalog.Dados.dados
import com.example.cinequiz.catalog.ErrorApi
import com.example.cinequiz.model.MovieCredits.MovieCredits
import com.example.cinequiz.model.SeriesCredits.SeriesCredits
import com.example.cinequiz.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {

    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val creditsLiveData : MutableLiveData<MovieCredits> by lazy { MutableLiveData<MovieCredits>() }
    val seriesCreditsLiveData : MutableLiveData<SeriesCredits> by lazy { MutableLiveData<SeriesCredits>() }

    private val repository = MoviesRepository()

    fun creditsList() = CoroutineScope(IO).launch {
        try {
            repository.getMovieCredits().let {
                creditsLiveData.postValue(it)
                Dados.postActors(it.cast)
            }
        }
        catch (error: Throwable) {
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


}