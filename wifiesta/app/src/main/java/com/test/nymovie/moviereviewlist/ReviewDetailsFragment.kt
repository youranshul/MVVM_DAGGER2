package com.test.nymovie.moviereviewlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.nymovie.R
import dagger.android.support.DaggerFragment

class ReviewDetailsFragment : DaggerFragment() {

    companion object {
        private const val TITLE = "movieTitle"
        private const val REVIEWER = "reviewer"

        fun newInstance(data: ReviewQueryData): Fragment {
            val fragment = ReviewDetailsFragment()
            val bundle = Bundle()
            bundle.putString(TITLE, data.title)
            bundle.putString(REVIEWER, data.reviewer)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.review_details, container, false)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.app_bar_search).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }
}
