package com.test.nymovie.nymovie.reviewdetails

import com.test.nymovie.DataMapper
import com.test.nymovie.reviewdetails.ReviewDetails
import javax.inject.Inject

class ReviewDetailsResponseMapper @Inject constructor() :
    DataMapper<ReviewDetailsResponse, ReviewDetails?> {
    override fun transform(data: ReviewDetailsResponse): ReviewDetails? {

        var reviewDetails: ReviewDetails? = null
        val datalist = data.results
        datalist.forEach { review ->
            val displayTitle = review.title
            displayTitle?.let { title ->
                val headline = parseNonNullValue(review.headline)
                val pick = isCriticksPick(review.pick)
                val byline = parseNonNullValue(review.reviewer)
                val imageUrl = getImageUrl(review.media)
                val rating = parseNonNullValue(review.rating)
                val summary = parseNonNullValue(review.summary)
                val publishedOn = parseNonNullValue(review.publish)
                val webLink = getWebLink(review.link)
                reviewDetails = ReviewDetails(
                    title, headline, pick, byline, imageUrl,
                    rating, summary, publishedOn, webLink
                )
            }
        }

        return reviewDetails
    }

    private fun getWebLink(link: ReadLink?): String {
        return link?.let {
            it.url?.let { url ->
                url
            } ?: kotlin.run { "" }
        } ?: kotlin.run { "" }
    }

    private fun getImageUrl(media: Multimedia?): String {
        return media?.let {
            it.imageUrl?.let { url ->
                url
            } ?: kotlin.run { "" }
        } ?: kotlin.run { "" }
    }

    private fun isCriticksPick(pick: Int): Boolean {
        return pick == 1
    }

    private fun parseNonNullValue(field: String?): String {
        return field?.let {
            it
        } ?: kotlin.run {
            ""
        }
    }
}