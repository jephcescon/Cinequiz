package com.example.cinequiz.catalog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinequiz.MainActivity
import com.example.cinequiz.R
import com.example.cinequiz.catalog.details.MovieDetails
import com.example.cinequiz.search.SearchMenu
import com.synnapps.carouselview.CarouselView

class CatalogFragment : Fragment() {

    val search by lazy { view?.findViewById<Button>(R.id.searchButton) }
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
        clickNavigation()
    }

    private fun clickNavigation() {
        navigationView?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                TODO("Arthur")
                R.id.movie -> {
                    Log.d("click", "Clicou em algo")
                    true
                }
                TODO("")
                R.id.movie_fantasy -> {
                    Log.d("click", "Clicou em algo")
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
                TODO("Jepherson")
                R.id.series -> {
                    Log.d("click", "Clicou em algo")
                    true
                }
                TODO("Pedro")
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

    private fun createdView(view: View) {

        //recycle movie
        bannerRecycle?.layoutManager = GridLayoutManager(view.context, 2)

        viewModel.movieLiveData.observe(viewLifecycleOwner) { movie->
            bannerRecycle?.adapter = CatalogAdapter(movie) { _-> }
        }
        bannerRecycle?.let { ViewCompat.setNestedScrollingEnabled(it, false) };

        //carousel
        val toggle = ActionBarDrawerToggle(
            view.context as Activity,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout?.addDrawerListener(toggle)
        viewModel.movieLiveData.observe(viewLifecycleOwner) { movie->
            val image = intArrayOf()
            carousel?.setImageListener { position, imageView ->
                imageView.setImageResource(image[position])
            }
            carousel?.pageCount = image.size
            carousel?.setImageClickListener { position->
            }
        }

        //BOTÃO DE PESQUISA
        search?.setOnClickListener {
            val intent = Intent(view.context, SearchMenu::class.java)
            startActivity(intent)
        }

    }

}
