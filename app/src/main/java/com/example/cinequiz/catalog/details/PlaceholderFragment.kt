package com.example.cinequiz.catalog.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.cinequiz.R

/**
 * A placeholder fragment containing a simple view.
 */

class PlaceholderFragment : Fragment() {

    companion object {
        const val FRAGMENT_NAME = "fragment_name"
        const val BACKGROUND_COLOR = "background_color"
        const val FRAGMENT_TITLE = "title"

        @JvmStatic
        fun newInstance(name: String, title: String): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putString(FRAGMENT_NAME, name);
                    putString(FRAGMENT_TITLE, title)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //inflando layout
        return inflater.inflate(R.layout.fragment_synopsis_cast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            view.findViewById<TextView>(R.id.tv_sinopse_elenco).text =
                    it.getString(FRAGMENT_NAME, "Empty name")

            view.findViewById<ConstraintLayout>(R.id.parent).setBackgroundColor(
                    ContextCompat.getColor(
                            requireContext(), it.getInt(
                            BACKGROUND_COLOR,
                            R.color.black
                    )
                    )
            )

        }

        super.onViewCreated(view, savedInstanceState)
    }
}