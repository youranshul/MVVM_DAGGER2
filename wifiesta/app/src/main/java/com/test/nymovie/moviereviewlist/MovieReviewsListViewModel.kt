package com.test.nymovie.moviereviewlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.nymovie.ScreenNavigation
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MovieReviewsListViewModel @Inject constructor(
    private val interactor: GetFetchReviewsInteractor,
    private val navigator: ScreenNavigation<ReviewQueryData>
) :
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

    fun onQueryChanged(newText: String?) {
        newText?.let {
            if (newText.length > 2) {
                Log.i("Ansh:", "search : $newText")
            }
        }
    }

    fun onItemClick(title: String, reviewer: String) {
        val data = ReviewQueryData(title, reviewer)
        navigator.navigateTo(data)
    }
}