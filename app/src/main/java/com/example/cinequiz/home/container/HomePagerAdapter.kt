package com.example.cinequiz.home.container

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cinequiz.home.AchievementsFragment
import com.example.cinequiz.home.recentMatch.RecentMatchesFragment
import com.example.cinequiz.home.recentSearch.RecentSearchesFragment


class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 ->
                RecentSearchesFragment()
            else -> {
                return RecentMatchesFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Buscas Recentes"
            else -> {
                return "Partidas Recentes"
            }
        }
    }

}