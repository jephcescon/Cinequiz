package com.example.cinequiz.catalog

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.example.cinequiz.catalog.details.MovieDetails
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView

class Navigation(viewModel: CatalogViewModel) {

    private val recycleScroll by lazy {
        RecycleScroll {
            viewModel.popularMoviesNextPage()
        }
    }

    private val recycleScrollGenreMovie by lazy {
        RecycleScroll {
            viewModel.genreMoviesNextPage()
        }
    }

    private val recycleScrollSearch by lazy {
        RecycleScroll {
            viewModel.searchNextPage()
        }
    }

    fun firstTime(
        bannerRecycle: RecyclerView?,
        viewModel: CatalogViewModel,
        carousel: CarouselView?,
        viewLifecycleOwner: LifecycleOwner,
        view: View
    ) {

        carousel?.visibility = VISIBLE
        viewModel.nextPage = 0

        //CallBack para passar o ID
        val adapter = CatalogAdapter {
            val intent = Intent(view.context, MovieDetails::class.java)
            Dados.postAll(it)
            view.context.startActivity(intent)
        }
        bannerRecycle?.adapter = adapter
        bannerRecycle?.layoutManager = GridLayoutManager(view.context, 2)

        bannerRecycle?.addOnScrollListener(recycleScroll)

        adapter.resetRecycle()
        viewModel.movieLiveData.value = listOf()
        viewModel.movieList()

        viewModel.movieLiveData.observe(viewLifecycleOwner) { popularMovies ->
            Log.d("pagina", "Movie = ${viewModel.nextPage}")
            recycleScroll.requesting = false

            val banners = mutableListOf<ImageRecycle>()
            popularMovies.forEach {
                banners.add(
                    ImageRecycle(
                        it.posterPath,
                        it.id,
                        true,
                        it.overview,
                        it.voteAverage,
                        it.originalTitle,
                        it.backdropPath
                    )
                )
            }
            adapter.addMovies(banners)
        }


        viewModel.carouselLiveData.observe(viewLifecycleOwner) { popularMovies ->
            val url = listOf(
                "https://image.tmdb.org/t/p/w500${popularMovies[0].backdropPath}",
                "https://image.tmdb.org/t/p/w500${popularMovies[1].backdropPath}",
                "https://image.tmdb.org/t/p/w500${popularMovies[2].backdropPath}",
                "https://image.tmdb.org/t/p/w500${popularMovies[3].backdropPath}"
            )
            carousel?.setImageListener { position, imageView ->
                Picasso.get().load(url[position]).into(imageView)
            }
            carousel?.pageCount = popularMovies.size
            //CallBack para passar o ID
            carousel?.setImageClickListener {
                val date = ImageRecycle(
                    popularMovies[it].posterPath,
                    popularMovies[it].id,
                    true,
                    popularMovies[it].overview,
                    popularMovies[it].voteAverage,
                    popularMovies[it].title,
                    popularMovies[it].backdropPath
                )
                val intent = Intent(view.context, MovieDetails::class.java)
                Dados.postAll(date)
                view.context.startActivity(intent)
            }
        }
    }

    fun clickNavigation(
        navigationView: NavigationView,
        bannerRecycle: RecyclerView?,
        viewModel: CatalogViewModel,
        carousel: CarouselView?,
        viewLifecycleOwner: LifecycleOwner,
        view: View
    ) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.movie -> {
                    firstTime(bannerRecycle, viewModel, carousel, viewLifecycleOwner, view)
                    true
                }
                R.id.movie_horror -> {
                    clickMovieGenre(
                        bannerRecycle,
                        view,
                        viewModel,
                        viewLifecycleOwner,
                        carousel,
                        "27"
                    )
                    true
                }
                R.id.movie_romance -> {
                    clickMovieGenre(
                        bannerRecycle,
                        view,
                        viewModel,
                        viewLifecycleOwner,
                        carousel,
                        "10749"
                    )
                    true
                }
                R.id.movie_scifi -> {
                    clickMovieGenre(
                        bannerRecycle,
                        view,
                        viewModel,
                        viewLifecycleOwner,
                        carousel,
                        "878"
                    )
                    true
                }
                R.id.movie_fantasy -> {
                    clickMovieGenre(
                        bannerRecycle,
                        view,
                        viewModel,
                        viewLifecycleOwner,
                        carousel,
                        "28"
                    )
                    true
                }
                R.id.movie_thriller -> {
                    clickMovieGenre(
                        bannerRecycle,
                        view,
                        viewModel,
                        viewLifecycleOwner,
                        carousel,
                        "53"
                    )
                    true
                }
                R.id.series -> {
                    true
                }
                R.id.series_fantasy -> {
                    Log.d("click", "Clicou em algo")
                    true
                }
                R.id.series_horror -> {
                    Log.d("click", "Clicou em algo")
                    true
                }
                R.id.series_romance -> {
                    Log.d("click", "Clicou em algo")
                    true
                }
                R.id.series_scifi -> {
                    Log.d("click", "Clicou em algo")
                    true
                }
                R.id.series_thriller -> {
                    Log.d("click", "Clicou em algo")
                    true
                }
                else -> {
                    Log.d("click", "Não cliclou em nada")
                    false
                }
            }
        }
    }

    internal fun search(
        bannerRecycle: RecyclerView?,
        view: View,
        viewModel: CatalogViewModel,
        viewLifecycleOwner: LifecycleOwner,
        carousel: CarouselView?,
        searchField: String
    ) {

        viewModel.searchNextPage = 0

        val adapter = CatalogAdapter {
            val intent = Intent(view.context, MovieDetails::class.java)
            Dados.postAll(it)
            view.context.startActivity(intent)
        }
        bannerRecycle?.adapter = adapter
        bannerRecycle?.layoutManager = GridLayoutManager(view.context, 2)

        bannerRecycle?.addOnScrollListener(recycleScrollSearch)

        adapter.resetRecycle()
        viewModel.searchLiveData.value = listOf()
        viewModel.genre = searchField
        viewModel.searchList()

        viewModel.searchLiveData.observe(viewLifecycleOwner) { listMovies ->
            recycleScrollSearch.requesting = false

            val banners = mutableListOf<ImageRecycle>()
            listMovies.forEach {
                banners.add(
                    ImageRecycle(
                        it.posterPath,
                        it.id,
                        true,
                        it.overview,
                        it.voteAverage,
                        it.title,
                        it.backdropPath
                    )
                )
            }
            adapter.addMovies(banners)
        }
        viewModel.searchCarouselLiveData.observe(viewLifecycleOwner) { categoryMovies ->
            val url = mutableListOf<String>()
//                "https://image.tmdb.org/t/p/w500${categoryMovies[0].backdropPath}",
//                "https://image.tmdb.org/t/p/w500${categoryMovies[1].backdropPath}",
//                "https://image.tmdb.org/t/p/w500${categoryMovies[2].backdropPath}",
//                "https://image.tmdb.org/t/p/w500${categoryMovies[3].backdropPath}"
//            )
            if (categoryMovies.isEmpty()){
                carousel?.visibility = GONE
            }else {
                categoryMovies.forEach {
                    if (it.backdropPath.isNullOrBlank()) {
                        url.add(R.drawable.semimagem.toString())
                    } else {
                        url.add("https://image.tmdb.org/t/p/w500${it.backdropPath}")
                    }
                }

                carousel?.setImageListener { position, imageView ->
                    if (url[position].contains("https"))
                        Picasso.get().load(url[position]).into(imageView)
                    else
                        imageView.setImageResource(url[position].toInt())
                }
                carousel?.pageCount = categoryMovies.size
                //CallBack para passar o ID
                carousel?.setImageClickListener {
                    val date = ImageRecycle(
                        categoryMovies[it].posterPath,
                        categoryMovies[it].id,
                        true,
                        categoryMovies[it].overview,
                        categoryMovies[it].voteAverage,
                        categoryMovies[it].title,
                        categoryMovies[it].backdropPath
                    )
                    val intent = Intent(view.context, MovieDetails::class.java)
                    Dados.postAll(date)
                    view.context.startActivity(intent)
                }
            }
        }
    }

    private fun clickMovieGenre(
        bannerRecycle: RecyclerView?,
        view: View,
        viewModel: CatalogViewModel,
        viewLifecycleOwner: LifecycleOwner,
        carousel: CarouselView?,
        genre: String
    ) {

        carousel?.visibility = VISIBLE
        viewModel.genreNextPage = 0

        val adapter = CatalogAdapter {
            val intent = Intent(view.context, MovieDetails::class.java)
            Dados.postAll(it)
            view.context.startActivity(intent)
        }
        bannerRecycle?.adapter = adapter
        bannerRecycle?.layoutManager = GridLayoutManager(view.context, 2)

        bannerRecycle?.addOnScrollListener(recycleScrollGenreMovie)

        adapter.resetRecycle()
        viewModel.movieGenreLiveData.value = listOf()
        viewModel.genre = genre
        viewModel.genreMovieList()

        viewModel.movieGenreLiveData.observe(viewLifecycleOwner) { listMovies ->
            Log.d("pagina", "Genre fantasy = ${viewModel.genreNextPage}")
            recycleScrollGenreMovie.requesting = false

            val banners = mutableListOf<ImageRecycle>()
            listMovies.forEach {
                banners.add(
                    ImageRecycle(
                        it.posterPath,
                        it.id,
                        true,
                        it.overview,
                        it.voteAverage,
                        it.title,
                        it.backdropPath
                    )
                )
            }
            adapter.addMovies(banners)
        }
        viewModel.carouselGenreLiveData.observe(viewLifecycleOwner) { categoryMovies ->
            val url = listOf(
                "https://image.tmdb.org/t/p/w500${categoryMovies[0].backdropPath}",
                "https://image.tmdb.org/t/p/w500${categoryMovies[1].backdropPath}",
                "https://image.tmdb.org/t/p/w500${categoryMovies[2].backdropPath}",
                "https://image.tmdb.org/t/p/w500${categoryMovies[3].backdropPath}"
            )
            carousel?.setImageListener { position, imageView ->
                Picasso.get().load(url[position]).into(imageView)
            }
            carousel?.pageCount = categoryMovies.size
            //CallBack para passar o ID
            carousel?.setImageClickListener {
                val date = ImageRecycle(
                    categoryMovies[it].posterPath,
                    categoryMovies[it].id,
                    true,
                    categoryMovies[it].overview,
                    categoryMovies[it].voteAverage,
                    categoryMovies[it].title,
                    categoryMovies[it].backdropPath
                )
                val intent = Intent(view.context, MovieDetails::class.java)
                Dados.postAll(date)
                view.context.startActivity(intent)
            }
        }
    }
}