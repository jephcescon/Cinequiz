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
import com.example.cinequiz.catalog.Dados.dados
import com.example.cinequiz.model.MovieCredits.Cast
import com.example.cinequiz.search.SearchMenu

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso


class MovieDetails : AppCompatActivity() {

    val viewPager by lazy { findViewById<ViewPager2>(R.id.vp_movie_details) }
    val tabs by lazy { findViewById<TabLayout>(R.id.tb_movie_details) }
    val back by lazy { findViewById<Button>(R.id.bt_return_movie_details) }
    val search by lazy { findViewById<Button>(R.id.bt_search_movie_details) }
    val movieImage by lazy { findViewById<ImageView>(R.id.iv_movie_img) }
    val movieTitle by lazy { findViewById<TextView>(R.id.tv_movie_title) }
    val viewModelMovieDetails by lazy { ViewModelProvider(this).get(MovieDetailsViewModel::class.java) }
    var result = mutableListOf<Cast>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)




        movieTitle.text = dados?.originalTitle
        val url = "https://image.tmdb.org/t/p/w500${dados?.banner}"
        Picasso.get().load(url).into(movieImage)



//        viewModelMovieDetails.creditsLiveData.observe(this, Observer {
//            it.cast.let { itchar -> result.addAll(itchar)
//            }
//            Log.d("Dados3", it.cast[0].name)
//            Log.d("Dados4", result.size.toString())
//        })



        viewModelMovieDetails.creditsLiveData.observe(this) { listAtores ->
            result.addAll(listAtores.cast)
            Log.d("Dados7", result.size.toString())
        }

        Log.d("Dados8", result.size.toString())


        val fragments = getFragments()
        val sectionsPagerAdapter = SectionsPagerAdapter(fragments, this)

        viewPager.adapter = sectionsPagerAdapter
        viewPager.offscreenPageLimit = 1

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = fragments[position].arguments?.getString(
                    PlaceholderFragment.FRAGMENT_TITLE,
                    "Empty name"
            )
        }.attach()


        back.setOnClickListener {
            super.onBackPressed()
        }

        search.setOnClickListener {
            val intent = Intent(this, SearchMenu::class.java)
            startActivity(intent)
        }

    }

    private fun getFragments(): List<Fragment> {
        return listOf(
                PlaceholderFragment.newInstance(
                        dados?.synopses.toString(), "Sinopse"
                ),
                PlaceholderFragment.newInstance(
                        "Elenco", "Elenco"
                )
        )
    }

}