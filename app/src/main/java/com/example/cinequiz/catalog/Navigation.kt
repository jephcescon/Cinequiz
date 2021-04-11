package com.example.cinequiz.catalog

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView

class Navigation(viewModel: CatalogViewModel) {

    val recycleScroll by lazy {
        RecycleScroll {
            viewModel.popularMoviesNextPage()
        }
    }

    val recycleScrollGenreMovie by lazy {
        RecycleScroll {
            viewModel.genreMoviesNextPage()
        }
    }

    fun firstTime(
        bannerRecycle: RecyclerView?,
        viewModel: CatalogViewModel,
        carousel: CarouselView?,
        viewLifecycleOwner: LifecycleOwner,
        view: View
    ) {

        viewModel.nextPage = 0

        //CallBack para passar o ID
        val adapter = CatalogAdapter {
            Dados.postAll(it)
        }
        bannerRecycle?.adapter = adapter
        bannerRecycle?.layoutManager = GridLayoutManager(view.context, 2)

        bannerRecycle?.addOnScrollListener(recycleScroll)

        adapter.resetRecycle()
        viewModel.movieLiveData.value = listOf()
        viewModel.movieList()

        viewModel.movieLiveData.observe(viewLifecycleOwner) { popularMovies ->
            Log.d("pagina","Movie = ${viewModel.nextPage}")
            recycleScroll.requesting = false

            val banners = mutableListOf<ImageRecycle>()
            popularMovies.forEach {
                banners.add(ImageRecycle(it.posterPath, it.id, true, it.overview, it.voteAverage))
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
            carousel?.setImageClickListener { position ->
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
                    clickMovieGenre(bannerRecycle, view, viewModel, viewLifecycleOwner, carousel,"27")
                    true
                }
                R.id.movie_romance -> {
                    clickMovieGenre(bannerRecycle, view, viewModel, viewLifecycleOwner, carousel,"10749")
                    true
                }
                R.id.movie_scifi -> {
                    clickMovieGenre(bannerRecycle, view, viewModel, viewLifecycleOwner, carousel,"878")
                    true
                }
                R.id.movie_fantasy -> {
                    clickMovieGenre(bannerRecycle, view, viewModel, viewLifecycleOwner, carousel,"28")
                    true
                }
                R.id.movie_thriller -> {
                    clickMovieGenre(bannerRecycle, view, viewModel, viewLifecycleOwner, carousel,"53")
                    true
                }
                R.id.series -> {
                    Log.d("click", "Clicou em algo")
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

    private fun clickMovieGenre(
        bannerRecycle: RecyclerView?,
        view: View,
        viewModel: CatalogViewModel,
        viewLifecycleOwner: LifecycleOwner,
        carousel: CarouselView?,
        genre:String
    ) {

        viewModel.genreNextPage = 0

        val adapter = CatalogAdapter { _ -> }
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
                        it.voteAverage
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
            carousel?.setImageClickListener { position ->
            }
        }
    }
}