package com.example.cinequiz.catalog.details

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.catalog.Dados.dados
import com.example.cinequiz.catalog.ErrorApi
import com.example.cinequiz.model.MovieCredits.MovieCredits
import com.example.cinequiz.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {

    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val creditsLiveData : MutableLiveData<MovieCredits> by lazy { MutableLiveData<MovieCredits>() }

    private val repository = MoviesRepository()

    init {
        creditsList()
    }


    fun creditsList() = CoroutineScope(IO).launch {
        try {
            repository.getMovieCredits().let {
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