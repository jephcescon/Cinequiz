package com.example.cinequiz.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cinequiz.R


class ProfileFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linkAboutApp : TextView by lazy {view.findViewById(R.id.textview_about)}
        val linkHelpProfile : TextView by lazy {view.findViewById(R.id.textview_help)}

        linkAboutApp.setOnClickListener {
            val intent = Intent(it.context, AboutApp::class.java)
            startActivity(intent)
            }
        linkHelpProfile.setOnClickListener {
            val intent = Intent(it.context, HelpProfile::class.java)
            startActivity(intent)
            }

        }

}




