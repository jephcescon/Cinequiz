package com.example.cinequiz.catalog.details

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionBarContextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.cinequiz.MainActivity
import com.example.cinequiz.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MovieDetails : AppCompatActivity() {

    val viewPager by lazy { findViewById<ViewPager2>(R.id.vp_movie_details) }
    val tabs by lazy { findViewById<TabLayout>(R.id.tb_movie_details) }
    val bottomNav by lazy { findViewById<BottomNavigationView>(R.id.bottomNavigationView_movie_details) }
    val back by lazy { findViewById<FloatingActionButton>(R.id.fb_back) }
    val search by lazy { findViewById<FloatingActionButton>(R.id.fb_serch) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        // ****COLOCANDO ACTIONBAR NO PROJETO****
        val action = supportActionBar!!
        action.setDisplayShowCustomEnabled(true)
        action.setCustomView(R.layout.actionbar)



        val fragments = getFragments()
        val sectionsPagerAdapter = SectionsPagerAdapter(fragments, this)

        viewPager.adapter = sectionsPagerAdapter
        viewPager.offscreenPageLimit = 1

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = fragments[position].arguments?.getString(
                    PlaceholderFragment.FRAGMENT_NAME,
                    "Empty name"
            )
        }.attach()


        back.setOnClickListener {
            super.onBackPressed()
        }

    }

    private fun getFragments(): List<Fragment> {
        return listOf(
                PlaceholderFragment.newInstance(
                        "Sinopse",
                        R.color.design_default_color_on_secondary
                ),
                PlaceholderFragment.newInstance(
                        "Elenco",
//                        R.color.design_default_color_error
                        R.color.design_default_color_on_secondary
                )
        )
    }

}