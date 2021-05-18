package com.example.cinequiz.search.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinequiz.catalog.ErrorApi
import com.example.cinequiz.model.popularMovieModel.PopularMoviesList
import com.example.cinequiz.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val movies: MutableLiveData<PopularMoviesList> by lazy { MutableLiveData<PopularMoviesList>() }

    private val repository = MoviesRepository()


    private fun moviesData(idMovies: Int) = CoroutineScope(IO).launch {
        try {
            repository.getMovie(idMovies).let { moviesPopular ->
                movies.postValue(moviesPopular)
//                    Dados.postTitle(moviesPopular.title!!)
//                    Dados.postMovieID(moviesPopular.id!!)
                Log.d("deu", "movies")
            }

        } catch (error: Throwable) {
            ErrorApi(error, errorMessage)
            Log.d("deu", errorMessage.toString())
        }
    }

    fun searchMovies(moviesIDsFirebase: ArrayList<Int>) {
        val mmoviesIDsFirebase = listOf<Int>(732439, 516714, 456768, 9618, 1769, 323660, 24428)
        mmoviesIDsFirebase.forEach {
            moviesData(it)


        }
    }

}