package com.test.nymovie.moviereviewlist

import io.reactivex.Single

interface NyMovieReviewService {

    fun loadReviews(): Single<MutableList<MovieReview>>
}