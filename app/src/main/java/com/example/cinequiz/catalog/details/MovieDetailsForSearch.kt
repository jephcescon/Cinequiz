package com.example.cinequiz.catalog.details

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.cinequiz.R
import com.example.cinequiz.search.activity.SearchMenu
import com.example.cinequiz.search.viewModel.SearchViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso


class MovieDetailsForSearch : AppCompatActivity() {

    private val viewPager by lazy { findViewById<ViewPager2>(R.id.vp_movie_details) }
    private val tabs by lazy { findViewById<TabLayout>(R.id.tb_movie_details) }
    private val back by lazy { findViewById<Button>(R.id.bt_return_movie_details) }
    private val search by lazy { findViewById<Button>(R.id.bt_search_movie_details) }
    private val movieImage by lazy { findViewById<ImageView>(R.id.iv_movie_img) }
    private val movieTitle by lazy { findViewById<TextView>(R.id.tv_movie_title) }
    private val voteAvarage by lazy { findViewById<TextView>(R.id.tv_voteAvarage) }
    private val searchViewModel by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val intent = intent.extras
        val type = intent?.getBoolean("MOVIE")
        val id = intent!!.getInt("ID")


        if (type == true) {
            searchViewModel.moviesData()
            searchViewModel.creditsList()

            searchViewModel.movies.observe(this) { movieData ->


                movieTitle.text = movieData.title
                val url = "https://image.tmdb.org/t/p/w500${movieData.backdropPath}"
                Picasso.get().load(url).into(movieImage)
                voteAvarage.text = movieData.voteAverage.toString()


                searchViewModel.creditsLiveData.observe(this) { listAtores ->

                    var cast = ""
                    listAtores.cast.forEach {
                        cast += "${it.name}  como: ${it.character} \n\n"
                    }


                    val fragments = getFragments(cast, movieData.overview)
                    val sectionsPagerAdapter = SectionsPagerAdapter(fragments, this)

                    viewPager.adapter = sectionsPagerAdapter
                    viewPager.offscreenPageLimit = 1

                    TabLayoutMediator(tabs, viewPager) { tab, position ->
                        tab.text = fragments[position].arguments?.getString(
                            PlaceholderFragment.FRAGMENT_TITLE,
                            "Empty name"
                        )
                    }.attach()
                }
            }
        }else{
            searchViewModel.creditsListTv()
            searchViewModel.seriesData(id)

            searchViewModel.series.observe(this) { serieData ->


                movieTitle.text = serieData.name
                val url = "https://image.tmdb.org/t/p/w500${serieData.backdropPath}"
                Picasso.get().load(url).into(movieImage)
                voteAvarage.text = serieData.voteAverage.toString()


                searchViewModel.seriesCreditsLiveData.observe(this) { listAtores ->

                    var cast = ""
                    listAtores.cast.forEach {
                        cast += "${it.name}  como: ${it.character} \n\n"
                    }

                    val fragments = getFragments(cast, serieData.overview)
                    val sectionsPagerAdapter = SectionsPagerAdapter(fragments, this)

                    viewPager.adapter = sectionsPagerAdapter
                    viewPager.offscreenPageLimit = 1

                    TabLayoutMediator(tabs, viewPager) { tab, position ->
                        tab.text = fragments[position].arguments?.getString(
                            PlaceholderFragment.FRAGMENT_TITLE,
                            "Empty name"
                        )
                    }.attach()
                }
            }
        }



        back.setOnClickListener {
            super.onBackPressed()
        }

        search.setOnClickListener {
            val intent = Intent(this, SearchMenu::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun getFragments(cast: String, overView: String): List<Fragment> {
        return listOf(
            PlaceholderFragment.newInstance(
                overView, "Sinopse"
            ),
            PlaceholderFragment.newInstance(
                cast, "Elenco"
            )
        )
    }

}