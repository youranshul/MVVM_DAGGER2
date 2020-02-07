package com.test.nymovie.moviereviewlist

import org.junit.Assert
import org.junit.Test

class MovieReviewShould {

    private lateinit var movieReview: MovieReview
    private val title = "testTitle"

    private val byline = "testByline"

    private val headline = "testHeadline"

    private val imageUrl = "testUrl"

    private val isPickedTrue = true

    @Test
    fun `set movie review data class`() {
        movieReview = MovieReview(title, byline, headline, imageUrl, isPickedTrue)

        Assert.assertEquals(movieReview.title, title)
        Assert.assertEquals(movieReview.byline, byline)
        Assert.assertEquals(movieReview.headline, headline)
        Assert.assertEquals(movieReview.imageUrl, imageUrl)
        Assert.assertEquals(movieReview.isPicked, isPickedTrue)

    }
}