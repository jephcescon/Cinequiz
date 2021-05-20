package com.example.cinequiz.search.viewModel

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinequiz.R
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.catalog.ErrorApi
import com.example.cinequiz.model.popularMovieModel.PopularMoviesList
import com.example.cinequiz.repository.MoviesRepository
import com.example.cinequiz.search.adapter.ItemSearchViewHolder
import com.example.cinequiz.search.model.ItemSearch
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val movies: MutableLiveData<PopularMoviesList> by lazy { MutableLiveData<PopularMoviesList>() }

    private val repository = MoviesRepository()

    init {
        moviesData()
    }


    private fun moviesData() = CoroutineScope(Dispatchers.IO).launch {
        try {
            Log.d("IDs1", Dados.moviesIDsFromFirebase.size.toString())
            Dados.moviesIDsFromFirebase.forEach {
                Dados.postMovieID(it)
                repository.getMovieData().let { moviesPopular ->
                    movies.postValue(moviesPopular)
                }
                Dados.itemSearch.add(ItemSearch(movies.value?.backdropPath!!, movies.value?.title!!))
                Log.d("itemsearch", Dados.itemSearch.size.toString())
            }


        } catch (error: Throwable) {
            ErrorApi(error, errorMessage)
            Log.d("deu", errorMessage.toString())
        }
    }
}
