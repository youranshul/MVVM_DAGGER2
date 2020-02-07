package com.test.nymovie.moviereviewlist

import com.test.nymovie.R
import com.test.nymovie.ScreenNavigation
import com.test.nymovie.core.MainActivity
import com.test.nymovie.replaceFragment
import com.test.nymovie.reviewdetails.ReviewDetailsFragment
import javax.inject.Inject

class ReviewDetailsScreen @Inject constructor(private val activity: MainActivity) :
    ScreenNavigation<ReviewQueryData> {
    override fun navigateTo(data: ReviewQueryData) {
        activity.replaceFragment(R.id.main_fragment, ReviewDetailsFragment.newInstance(data))
    }
}