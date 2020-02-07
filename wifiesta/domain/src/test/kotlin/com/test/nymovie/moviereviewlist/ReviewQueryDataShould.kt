package com.test.nymovie.moviereviewlist

import org.junit.Assert
import org.junit.Test

class ReviewQueryDataShould {

    private val title = "testTitle"

    private val reviewer = "testReviewer"

    @Test
    fun `fill review query data`() {
        val reviewQueryData = ReviewQueryData(title, reviewer)

        Assert.assertEquals(title, reviewQueryData.title)
        Assert.assertEquals(reviewer, reviewQueryData.reviewer)
    }
}