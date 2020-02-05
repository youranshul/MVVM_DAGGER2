package com.test.nymovie.nymovie.moviereviewlist

import com.test.nymovie.DataMapper
import com.test.nymovie.moviereviewlist.MovieReview
import javax.inject.Inject

class MovieReviewsResponseMapper @Inject constructor() :
    DataMapper<MovieReviewResponse, MutableList<MovieReview>> {
    override fun transform(data: MovieReviewResponse): MutableList<MovieReview> {

        val reviews = mutableListOf<MovieReview>()
        data.results.forEach { review ->
            if(!review.title.isNullOrBlank()){
                val isPicked = review.pick == 1
                var reviewer = ""
                var headline = ""
                var src = ""

                review.reviewer?.let {
                    reviewer = it
                }
                review.headline?.let {
                    headline = it
                }
                review.media?.let {
                    it.imageUrl?.let {
                        src = it
                    }
                }

                val movieReview = MovieReview(review.title, reviewer, headline, src, isPicked)
                reviews.add(movieReview)
            }

        }

        return reviews
    }
}