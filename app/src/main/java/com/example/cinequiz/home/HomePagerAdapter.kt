package com.example.cinequiz.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cinequiz.home.recentMatch.RecentMatchesFragment


class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 ->
                AchievementsFragment()
            1 ->
                RecentSearchesFragment()
            else -> {
                return RecentMatchesFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Conquistas"
            1 -> "Buscas Recentes"
            else -> {
                return "Partidas Recentes"
            }
        }
    }

}