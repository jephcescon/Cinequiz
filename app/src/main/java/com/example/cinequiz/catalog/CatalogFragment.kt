package com.example.cinequiz.catalog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinequiz.MainActivity
import com.example.cinequiz.R
import com.synnapps.carouselview.CarouselView

class CatalogFragment : Fragment() {
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
                R.id.movie -> {
                    Log.d("click", "Clicou em algo")
                    true
                }
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

    private fun createdView(view: View) {

        //recycle movie
        bannerRecycle?.layoutManager = GridLayoutManager(view.context, 2)
        viewModel.movieLiveData.observe(viewLifecycleOwner) { movie->
            bannerRecycle?.adapter = CatalogAdapter(movie) { position ->
                val intent = Intent(view.context,MainActivity::class.java)
                intent.putExtra("BANNER", movie[position].imagem)
                startActivity(intent)
            }
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
            val image = intArrayOf(
                movie[0].imagem,
                movie[1].imagem,
                movie[2].imagem,
                movie[3].imagem,
                movie[4].imagem
            )
            carousel?.setImageListener { position, imageView ->
                imageView.setImageResource(image[position])
            }
            carousel?.pageCount = image.size
            carousel?.setImageClickListener { position->
                val intent = Intent(view.context,MainActivity::class.java)
                intent.putExtra("BANNER", movie[position].imagem)
                startActivity(intent)
            }
        }

    }

}
