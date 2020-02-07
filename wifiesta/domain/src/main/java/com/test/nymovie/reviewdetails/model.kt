package com.test.nymovie.reviewdetails

data class ReviewDetails(
    val title: String,
    val headline: String,
    val pick: Boolean,
    val byline: String,
    val imageUrl: String,
    val rating: String,
    val summary: String,
    val publishedOn: String,
    val webLink: String
)