package com.example.cinequiz.catalog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cinequiz.R
import com.example.cinequiz.search.FireManagerMovie.FireManager
import com.example.cinequiz.search.activity.SearchMenu
import com.example.cinequiz.search.model.ClickSearch
import com.google.android.material.snackbar.Snackbar
import com.synnapps.carouselview.CarouselView


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
    private val carrouselVisibility by lazy { view?.findViewById<ImageView>(R.id.carrouselVisibility) }

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

        carrouselVisibility?.setOnClickListener {
            if (carousel?.visibility == VISIBLE) {
                animationCloseCarrousel(carrouselVisibility!!)
            } else
                animationOpenCarrousel()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let {
                Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun animationOpenCarrousel() {
        carousel?.let {
            it.visibility = VISIBLE
            val animate = TranslateAnimation(
                0f,  // fromXDelta
                0f,  // toXDelta
                -it.height.toFloat(),  // fromYDelta
                0f
            ) // toYDelta

            animate.duration = 500
            animate.fillAfter = true
            it.startAnimation(animate)
            carrouselVisibility?.startAnimation(animate)
            bannerRecycle?.startAnimation(animate)

            Handler(Looper.myLooper()!!).postDelayed(
                {
                    carrouselVisibility?.setImageResource(R.drawable.close_view)
                }, 500
            )
        }
    }

    private fun animationCloseCarrousel(view: ImageView) {
        carousel?.let {
            val animate = TranslateAnimation(
                0f,  // fromXDelta
                0f,  // toXDelta
                0f,  // fromYDelta
                -it.height.toFloat()
            ) // toYDelta

            animate.duration = 500
            animate.fillAfter = true

            carrouselVisibility?.startAnimation(animate)
            it.startAnimation(animate)
            bannerRecycle?.startAnimation(animate)

            Handler(Looper.myLooper()!!).postDelayed(
                {
                    it.visibility = GONE
                    view.setImageResource(R.drawable.open_view)
                    val animate2 = TranslateAnimation(
                        0f,  // fromXDelta
                        0f,  // toXDelta
                        0f,  // fromYDelta
                        0f
                    ) // toYDelta
                    animate2.duration = 0
                    animate2.fillAfter = true
                    bannerRecycle?.startAnimation(animate2)
                    carrouselVisibility?.startAnimation(animate2)
                }, 500
            )
        }
    }

    private fun createdView(view: View) {
        val nav = Navigation(viewModel)
        //navegação
        navigationView?.let {
            nav.clickNavigation(
                it,
                bannerRecycle,
                viewModel,
                carousel,
                viewLifecycleOwner,
                view,
                drawerLayout,
                carrouselVisibility
            )
        }

        //recycle movie
        nav.firstTime(bannerRecycle, viewModel, carousel, viewLifecycleOwner, view)

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
            FireManager.getLastSearch()
            startActivity(intent)
        }

        ClickSearch.searchTrue.observe(viewLifecycleOwner) {
            if (it) {
                nav.search(
                    bannerRecycle,
                    view,
                    viewModel,
                    viewLifecycleOwner,
                    carousel,
                    ClickSearch.textSearch,
                    carrouselVisibility
                )
                ClickSearch.searchTrue.postValue(false)
            }
        }

    }

    fun closeDrawer() {
        if (drawerLayout?.isDrawerOpen(GravityCompat.START) == true)
            drawerLayout?.closeDrawer(GravityCompat.START)
    }
}
