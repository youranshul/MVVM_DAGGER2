package com.test.nymovie.reviewdetails

import com.test.nymovie.ScreenNavigation
import com.test.nymovie.core.MainActivity
import com.test.nymovie.openWeb
import javax.inject.Inject

class ReviewDetailsWebScreen @Inject constructor(private val activity: MainActivity) :
    ScreenNavigation<String> {
    override fun navigateTo(data: String) {
        activity.openWeb(data)
    }

}
