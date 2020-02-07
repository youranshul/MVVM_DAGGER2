package com.test.nymovie.nymovie.moviereviewlist

import com.test.nymovie.nymovie.reviewdetails.ReviewDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NyMovieReviewsApi {

    @GET("reviews/search.json")
    fun getReviews(): Single<MovieReviewResponse>

    @GET("reviews/search.json")
    fun getReviewDetails(@QueryMap map: HashMap<String, String>): Single<ReviewDetailsResponse>
}

