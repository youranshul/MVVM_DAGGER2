package com.test.nymovie.nymovie.moviereviewlist

import io.reactivex.Single
import retrofit2.http.GET

interface NyMoviesService {

    @GET("reviews/search.json")
    fun getReviews(): Single<MovieReviewResponse>
}

