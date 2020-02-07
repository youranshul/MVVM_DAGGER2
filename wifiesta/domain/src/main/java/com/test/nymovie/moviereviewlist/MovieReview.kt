package com.test.nymovie.moviereviewlist

data class MovieReview(
    val title: String,
    val byline: String,
    val headline: String,
    val imageUrl: String,
    val isPicked: Boolean
)

data class ReviewQueryData(val title: String, val reviewer: String)
