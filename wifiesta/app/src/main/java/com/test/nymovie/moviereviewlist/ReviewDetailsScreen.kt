package com.test.nymovie.moviereviewlist

import androidx.navigation.findNavController
import com.test.nymovie.ScreenNavigation
import com.test.nymovie.core.MainActivity
import javax.inject.Inject

class ReviewDetailsScreen @Inject constructor(private val activity: MainActivity) :
    ScreenNavigation<ReviewQueryData> {
    override fun navigateTo(data: ReviewQueryData) {
        //activity.replaceFragment(R.id.main_fragment, ReviewDetailsFragment.newInstance(data))
      //  activity.findNavController()

    }
}