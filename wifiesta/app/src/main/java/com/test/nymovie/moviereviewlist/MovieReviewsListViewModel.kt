package com.test.nymovie.moviereviewlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MovieReviewsListViewModel @Inject constructor(private val interactor: GetFetchReviewsInteractor) : ViewModel() {


    private val reviewLiveData = MutableLiveData<List<MovieReview>>()

    fun loadMovieReviews(): LiveData<List<MovieReview>> {

        interactor.execute(object: SingleObserver<MutableList<MovieReview>>{
            override fun onSuccess(t: MutableList<MovieReview>) {
                reviewLiveData.value = t
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
               e.printStackTrace()
            }
        })

        return reviewLiveData
    }
}