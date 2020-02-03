package com.test.nymovie.moviereviewlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MovieReviewsListViewModel @Inject constructor(private val interactor: GetFetchReviewsInteractor) :
    ViewModel() {


    private val reviewLiveData = MutableLiveData<List<MovieReview>>()
    val loadingBarLiveData = MutableLiveData<UiSignal>()

    private val compositeDisposable = CompositeDisposable()

    fun loadMovieReviews(): LiveData<List<MovieReview>> {

        interactor.execute(object : SingleObserver<MutableList<MovieReview>> {
            override fun onSuccess(t: MutableList<MovieReview>) {
                reviewLiveData.value = t
                loadingBarLiveData.value = UiSignal.LOADING_GONE
            }

            override fun onSubscribe(disposable: Disposable) {
                loadingBarLiveData.value = UiSignal.LOADING_VISIBLE
                compositeDisposable.add(disposable)
            }

            override fun onError(e: Throwable) {
                loadingBarLiveData.value = UiSignal.LOADING_GONE
                loadingBarLiveData.value = UiSignal.ERROR_MSG
                e.printStackTrace()
            }
        })

        return reviewLiveData
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}