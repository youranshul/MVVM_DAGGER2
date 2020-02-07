package com.test.nymovie.moviereviewlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.nymovie.ScreenNavigation
import com.test.nymovie.core.BaseViewModel
import javax.inject.Inject

class MovieReviewsListViewModel @Inject constructor(
    private val interactor: GetFetchReviewsInteractor,
    private val navigator: ScreenNavigation<ReviewQueryData>
) : BaseViewModel() {

    override fun provideUiSignalLiveData(): MutableLiveData<UiSignal> {
        return uiSignalLiveData
    }

    private val reviewLiveData = MutableLiveData<List<MovieReview>>()

    fun loadMovieReviews(): LiveData<List<MovieReview>> {
        interactor.execute(
            getSingleObserver({
                reviewLiveData.value = it
            })
        )
        return reviewLiveData
    }

    fun onItemClick(title: String, reviewer: String) {
        val data = ReviewQueryData(title, reviewer)
        navigator.navigateTo(data)
    }
}