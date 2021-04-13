package com.example.cinequiz.catalog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinequiz.R
import com.example.cinequiz.catalog.details.MovieDetails
import com.example.cinequiz.model.popularMovieModel.PopularMoviesList
import com.example.cinequiz.search.SearchMenu
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import okhttp3.internal.notify

class CatalogFragment : Fragment() {

    private val search by lazy { view?.findViewById<Button>(R.id.searchButton) }
    private val carousel by lazy { view?.findViewById<CarouselView>(R.id.carouselView) }
    private val bannerRecycle by lazy {
        view?.findViewById<androidx.recyclerview.widget.RecyclerView>(
            R.id.bannerRecycle
        )
    }
    private val drawerLayout by lazy { view?.findViewById<DrawerLayout>(R.id.catalogFragment) }
    private val toolbar by lazy { view?.findViewById<Toolbar>(R.id.toolbar) }
    private val navigationView by lazy {
        view?.findViewById<com.google.android.material.navigation.NavigationView>(
            R.id.navigationView
        )
    }

    lateinit var viewModel: CatalogViewModel

//    private val recycleScroll by lazy {
//        RecycleScroll {
//            viewModel.popularMoviesNextPage()
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CatalogViewModel::class.java)

        createdView(view)

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let {
                Snackbar.make(view,it,Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun createdView(view: View) {
        val nav = Navigation(viewModel)
        //navegação
        navigationView?.let { nav.clickNavigation(it,bannerRecycle,viewModel,carousel,viewLifecycleOwner,view) }

        //recycle movie
        nav.firstTime(bannerRecycle,viewModel,carousel,viewLifecycleOwner,view)


//        val adapter = CatalogAdapter{ _ -> }
//        bannerRecycle?.adapter = adapter
//        bannerRecycle?.layoutManager = GridLayoutManager(view.context,2)
//
//        bannerRecycle?.addOnScrollListener(recycleScroll)
//
//        viewModel.movieLiveData.observe(viewLifecycleOwner){ popularMovies->
//            setRequestingNextPage()
//            adapter.addMovies(popularMovies)
//        }

        //configurando view do menu sanduiche
        val toggle = ActionBarDrawerToggle(
            view.context as Activity,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout?.addDrawerListener(toggle)

        //BOTÃO DE PESQUISA
        search?.setOnClickListener {
            val intent = Intent(view.context, SearchMenu::class.java)
            startActivity(intent)
        }
    }

//    private fun setRequestingNextPage() {
//        recycleScroll.requesting = false
//    }
}
