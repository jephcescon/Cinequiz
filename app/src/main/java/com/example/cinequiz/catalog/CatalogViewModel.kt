package com.example.cinequiz.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinequiz.model.discoverMovies.ListMoviesGenre
import com.example.cinequiz.model.discoverMovies.MoviesByGenre
import com.example.cinequiz.model.popularMovieModel.PopularMovies
import com.example.cinequiz.model.popularMovieModel.PopularMoviesList
import com.example.cinequiz.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class CatalogViewModel: ViewModel() {
    val movieLiveData: MutableLiveData<List<PopularMoviesList>> by lazy { MutableLiveData<List<PopularMoviesList>>() }
    val carouselLiveData: MutableLiveData<List<PopularMoviesList>> by lazy { MutableLiveData<List<PopularMoviesList>>() }

    val movieGenreLiveData: MutableLiveData<List<ListMoviesGenre>> by lazy { MutableLiveData<List<ListMoviesGenre>>() }
    val carouselGenreLiveData: MutableLiveData<List<ListMoviesGenre>> by lazy { MutableLiveData<List<ListMoviesGenre>>() }
    lateinit var genre: String

    val startedLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val pagingLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    var nextPage = 0
    var genreNextPage = 0


    private val repository = MoviesRepository()

//    init {
//        movieList()
//    }


    private fun <T> separation(list: List<T>?): MutableList<List<T>> {
        val carouselRecycle = mutableListOf<List<T>>()
        val carousel = mutableListOf<T>()
        val recycle = mutableListOf<T>()
        var cont = 0
        list?.forEach {
            if (cont <= 3){
                carousel.add(it)
                cont++
            }else
                recycle.add(it)
        }
        carouselRecycle.add(carousel)
        carouselRecycle.add(recycle)
        return carouselRecycle
    }

    fun movieList() = CoroutineScope(IO).launch {
        try {
            startedLoading.postValue(true)
            repository.getPopularMovies().let { movies ->
                updateNextPage(movies)

                val separation = separation(movies.listMovies)
                movieLiveData.postValue(separation[1])
                carouselLiveData.postValue(separation[0])
            }
        }catch (error:Throwable){
            ErrorApi(error, errorMessage)
        }finally {
            startedLoading.postValue(false)
        }
    }

    private fun updateNextPage(movies: PopularMovies) {
        nextPage = movies.page?.plus(1) ?: 1
    }

    fun popularMoviesNextPage()= CoroutineScope(IO).launch {
        try {
            pagingLoading.postValue(true)
            repository.getPopularMovies(page = nextPage).let {
                updateNextPage(it)
                movieLiveData.postValue(it.listMovies)
            }
        }catch (error:Throwable){
            ErrorApi(error, errorMessage)
        }finally {
            pagingLoading.postValue(false)
        }
    }

    fun genreMovieList() = CoroutineScope(IO).launch {
        try {
            startedLoading.postValue(true)
            repository.getDiscoverMovies(genre = genre).let { movies->
                updateNextPageMoviesGenre(movies)

                val separation = separation(movies.listMoviesGenre)
                movieGenreLiveData.postValue(separation[1])
                carouselGenreLiveData.postValue(separation[0])
            }
        }catch (error:Throwable){
            ErrorApi(error, errorMessage)
        }finally {
            startedLoading.postValue(false)
        }
    }

    private fun updateNextPageMoviesGenre(movies: MoviesByGenre){
        genreNextPage = movies.page?.plus(1) ?: 1
    }

    fun genreMoviesNextPage() = CoroutineScope(IO).launch {
        try {
            pagingLoading.postValue(true)
            repository.getDiscoverMovies(page = genreNextPage,genre = genre).let { movies->
                updateNextPageMoviesGenre(movies)
                movieGenreLiveData.postValue(movies.listMoviesGenre)
            }
        }catch (error:Throwable){
            ErrorApi(error, errorMessage)
        }finally {
            pagingLoading.postValue(false)
        }
    }

}

fun ErrorApi(error: Throwable, errorMessage: MutableLiveData<String>){
    when (error){
        is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.message}")
        is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
    }
}
