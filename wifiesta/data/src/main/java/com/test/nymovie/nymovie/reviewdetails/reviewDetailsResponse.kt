package com.test.nymovie.nymovie.reviewdetails

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ReviewDetailsResponse(@JsonProperty("results") val results: List<Review>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Review(
    @JsonProperty("display_title") val title: String?,
    @JsonProperty("critics_pick") val pick: Int,
    @JsonProperty("byline") val reviewer: String?,
    @JsonProperty("headline") val headline: String?,
    @JsonProperty("multimedia") val media: Multimedia?,
    @JsonProperty("mpaa_rating") val rating: String?,
    @JsonProperty("summary_short") val summary: String?,
    @JsonProperty("publication_date") val publish: String?,
    @JsonProperty("link") val link: ReadLink?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ReadLink(
    @JsonIgnoreProperties("url") val url: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Multimedia(@JsonProperty("src") val imageUrl: String?)



