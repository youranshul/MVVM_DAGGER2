package com.test.nymovie.reviewdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.nymovie.ScreenNavigation
import com.test.nymovie.core.BaseViewModel
import com.test.nymovie.moviereviewlist.ReviewQueryData
import com.test.nymovie.moviereviewlist.UiSignal
import javax.inject.Inject

class ReviewDetailsViewModel @Inject constructor(
    private val interactor: GetReviewsDetailsInteractor,
    private val navigator: ScreenNavigation<String>
) :
    BaseViewModel() {

    override fun provideUiSignalLiveData(): MutableLiveData<UiSignal> {
        return uiSignalLiveData
    }

    private val detailsLiveData = MutableLiveData<ReviewDetails>()

    fun loadReviewDetails(title: String, reviewer: String): LiveData<ReviewDetails> {
        val data = ReviewQueryData(title, reviewer)
        interactor.execute(getSingleObserver({
            detailsLiveData.value = it
        }), data)

        return detailsLiveData
    }

    fun openWeb() {
        detailsLiveData.value?.webLink?.let { navigator.navigateTo(it) }
    }
}
