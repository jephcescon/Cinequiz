package com.example.cinequiz.home.container

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.cinequiz.R
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso


class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private val profileImage by lazy { view?.findViewById<ImageView>(R.id.home_profile_pic) }
    private val profileName by lazy { view?.findViewById<TextView>(R.id.user_name) }

    private val firebaseAuth = Firebase.auth
    private val firebaseStorage = Firebase.storage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.home_view_pager)
        tabs = view.findViewById(R.id.home_tab_layout)

        val fragmentAdapter = HomePagerAdapter(childFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)

        configUserInfo()
    }

    private fun configUserInfo() {
        val user = firebaseAuth.currentUser
        user?.let { userVerification->
            firebaseStorage.getReference("uploads")
                .child(user.uid)
                .downloadUrl
                .addOnSuccessListener { uri ->
                    Picasso.get().load(uri).into(profileImage)
                }.addOnFailureListener {
                    Picasso.get().load(userVerification.photoUrl).into(profileImage)
                }.addOnCompleteListener {
                    profileName?.text = "Olá ${userVerification.displayName} !"
                }
        }
    }
}
