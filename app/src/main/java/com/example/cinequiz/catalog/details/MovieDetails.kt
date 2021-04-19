package com.example.cinequiz.catalog.details

import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2

import com.example.cinequiz.R
import com.example.cinequiz.catalog.Dados
import com.example.cinequiz.catalog.Dados.dados
import com.example.cinequiz.model.MovieCredits.Cast
import com.example.cinequiz.search.SearchMenu

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso


class MovieDetails : AppCompatActivity() {

    private val viewPager by lazy { findViewById<ViewPager2>(R.id.vp_movie_details) }
    private val tabs by lazy { findViewById<TabLayout>(R.id.tb_movie_details) }
    private val back by lazy { findViewById<Button>(R.id.bt_return_movie_details) }
    private val search by lazy { findViewById<Button>(R.id.bt_search_movie_details) }
    private val movieImage by lazy { findViewById<ImageView>(R.id.iv_movie_img) }
    private val movieTitle by lazy { findViewById<TextView>(R.id.tv_movie_title) }
    private val voteAvarage by lazy { findViewById<TextView>(R.id.tv_voteAvarage) }
    private val viewModelMovieDetails by lazy { ViewModelProvider(this).get(MovieDetailsViewModel::class.java) }
    var result = mutableListOf<Cast>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)



        movieTitle.text = dados?.title
        val url = "https://image.tmdb.org/t/p/w500${dados?.backdrop}"
        Picasso.get().load(url).into(movieImage)
        voteAvarage.text = dados?.vote.toString()


        viewModelMovieDetails.creditsLiveData.observe(this) { listAtores ->
            result.addAll(listAtores.cast)
            

            var cast = ""
            listAtores.cast.forEach {
                cast += "${it.name}  como: ${it.character} \n\n"
            }


            val fragments = getFragments(cast)
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



        back.setOnClickListener {
            super.onBackPressed()
        }

        search.setOnClickListener {
            val intent = Intent(this, SearchMenu::class.java)
            startActivity(intent)
        }

    }

    fun getFragments(cast :String): List<Fragment> {
        Log.wtf("nome", dados?.synopses.toString())
        return listOf(
            PlaceholderFragment.newInstance(
                dados?.synopses.toString(), "Sinopse"
            ),
            PlaceholderFragment.newInstance(
                cast, "Elenco"
            )
        )
    }
}