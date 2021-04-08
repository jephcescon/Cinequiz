package com.example.cinequiz.catalog
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinequiz.R
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView

fun firstTime(bannerRecycle: RecyclerView?, viewModel: CatalogViewModel, carousel:CarouselView?, viewLifecycleOwner:LifecycleOwner, view:View){

    val recycleScroll by lazy {
        RecycleScroll {
            viewModel.popularMoviesNextPage()
        }
    }

    val adapter = CatalogAdapter{ _ -> }
    bannerRecycle?.adapter = adapter
    bannerRecycle?.layoutManager = GridLayoutManager(view.context,2)

    bannerRecycle?.addOnScrollListener(recycleScroll)

    viewModel.movieLiveData.observe(viewLifecycleOwner){ popularMovies->
        recycleScroll.requesting = false
        adapter.addMovies(popularMovies)
    }


    viewModel.carouselLiveData.observe(viewLifecycleOwner){ popularMovies->
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
        carousel?.setImageClickListener { position ->
        }
    }

//    viewModel.startMovieList()
//    bannerRecycle?.layoutManager = GridLayoutManager(view.context, 2)
//    val imageList = mutableListOf<ImageRecycle>()
//    bannerRecycle?.addOnScrollListener(recycleScroll)
//
//    viewModel.movieLiveData.observe(viewLifecycleOwner) { popularMovies ->
//        recycleScroll.requesting = false
//
//        val adapter = CatalogAdapter(imageList) { _ -> }
//        bannerRecycle?.adapter = adapter
//        popularMovies.forEach {
//            val recycleItem = ImageRecycle(it.posterPath , it.id,true)
//            imageList.add(recycleItem)
//        }
//        adapter.notifyDataSetChanged()
//    }

}

fun clickNavigation(navigationView:NavigationView,bannerRecycle: RecyclerView?, viewModel: CatalogViewModel, carousel:CarouselView?, viewLifecycleOwner:LifecycleOwner, view:View) {

    val recycleScroll by lazy {
        RecycleScroll {
            viewModel.popularMoviesNextPage()
        }
    }

    navigationView.setNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.movie -> {
                TODO("Achar uma maneira de limpar o livedata atual, mudar a função do viewmodel do init para uma função a ser chamada, mudar a lógica do adaptar para algo generico que retorne o ID")
                val adapter = CatalogAdapter { _ -> }
                bannerRecycle?.adapter = adapter
                bannerRecycle?.layoutManager = GridLayoutManager(view.context, 2)

                bannerRecycle?.addOnScrollListener(recycleScroll)

                adapter.resetRecycle()

                viewModel.movieLiveData.observe(viewLifecycleOwner) { popularMovies ->
                    recycleScroll.requesting = false
                    adapter.addMovies(popularMovies)
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
                    carousel?.setImageClickListener { position ->
                    }
                }
                true
            }
            R.id.movie_fantasy -> {
//                val adapter = CatalogAdapter { _ -> }
//                bannerRecycle?.adapter = adapter
//                bannerRecycle?.layoutManager = GridLayoutManager(view.context, 2)
//
//                bannerRecycle?.addOnScrollListener(recycleScroll)
//
//                adapter.resetRecycle()
//
//                viewModel.carouselLiveData.observe(viewLifecycleOwner) { popularMovies ->
//                    val url = listOf(
//                        "https://image.tmdb.org/t/p/w500${popularMovies[0].backdropPath}",
//                        "https://image.tmdb.org/t/p/w500${popularMovies[1].backdropPath}",
//                        "https://image.tmdb.org/t/p/w500${popularMovies[2].backdropPath}",
//                        "https://image.tmdb.org/t/p/w500${popularMovies[3].backdropPath}"
//                    )
//                    carousel?.setImageListener { position, imageView ->
//                        Picasso.get().load(url[position]).into(imageView)
//                    }
//                    carousel?.pageCount = popularMovies.size
//                    carousel?.setImageClickListener { position ->
//                    }
//                }
                true
            }
            R.id.movie_horror -> {
                Log.d("click", "Clicou em algo")
                true
            }
            R.id.movie_romance -> {
                Log.d("click", "Clicou em algo")
                true
            }
            R.id.movie_scifi -> {
                Log.d("click", "Clicou em algo")
                true
            }
            R.id.movie_thriller -> {
                Log.d("click", "Clicou em algo")
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