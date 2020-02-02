package com.test.nymovie.moviereviewlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MovieReviewsListViewModel @Inject constructor() : ViewModel() {

    fun loadMovieReviews(): LiveData<List<MovieReview>> {

        val list = mutableListOf<MovieReview>()
        list.add(MovieReview("Incitment", "A O Smith", "", ""))
        list.add(MovieReview("Jose", "Kenelr", "", ""))
        list.add(MovieReview("Connectivut", "Livery", "", ""))
        list.add(MovieReview("The Assistent", "A O Smith", "", ""))
        list.add(MovieReview("Club house", "Konnel", "", ""))


        val liveData = MutableLiveData<List<MovieReview>>()
        liveData.value = list
        return liveData
    }
}