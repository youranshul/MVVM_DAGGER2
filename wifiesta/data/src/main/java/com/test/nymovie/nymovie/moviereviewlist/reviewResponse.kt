package com.test.nymovie.nymovie.moviereviewlist

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieReviewResponse(@JsonProperty("results") val results: List<Review>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Review(
    @JsonProperty("display_title") val title: String?,
    @JsonProperty("critics_pick") val pick: Int,
    @JsonProperty("byline") val reviewer: String?,
    @JsonProperty("headline") val headline: String?,
    @JsonProperty("multimedia") val media: Multimedia?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Multimedia(@JsonProperty("src") val imageUrl: String?)



