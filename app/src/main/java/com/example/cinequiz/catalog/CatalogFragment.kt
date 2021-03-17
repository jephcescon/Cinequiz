package com.example.cinequiz.catalog

import android.app.Activity
import android.content.ClipData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinequiz.R
import com.synnapps.carouselview.CarouselView

class CatalogFragment : Fragment() {
    private val carousel by lazy {view?.findViewById<CarouselView>(R.id.carouselView)}
    private val bannerRecycle by lazy { view?.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.bannerRecycle) }
    private val drawerLayout by lazy { view?.findViewById<DrawerLayout>(R.id.catalogFragment) }
    private val navigationView by lazy { view?.findViewById<com.google.android.material.navigation.NavigationView>(R.id.navigationView) }
    private val toolbar by lazy { view?.findViewById<Toolbar>(R.id.toolbar) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val toggle = ActionBarDrawerToggle(view.context as Activity,drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout?.addDrawerListener(toggle)

        bannerRecycle?.layoutManager = GridLayoutManager(view.context,2)
        val adapter = listOf(41,4)
        bannerRecycle?.adapter = CatalogAdapter(adapter)
        carouselCreate()

    }

    private fun carouselCreate() {
        val image = intArrayOf(R.drawable.deadpool,R.drawable.ic_game,R.drawable.ic_movie)

        carousel?.setImageListener { position, imageView ->
            imageView.setImageResource(image[position])
        }
        carousel?.pageCount = image.size
    }
}
