package com.test.nymovie.reviewdetails

import com.nhaarman.mockitokotlin2.verify
import com.test.nymovie.moviereviewlist.ReviewQueryData
import org.junit.Test
import org.mockito.Mockito

class ReviewDetailsServiceShould{

    @Test
    fun `call loadReview from server`(){
        val nyReviewDetailsService = Mockito.mock(NyReviewDetailsService::class.java)
        val reviewDetailsService = ReviewDetailsService(nyReviewDetailsService)
        val data = Mockito.mock(ReviewQueryData::class.java)
        reviewDetailsService.fetchDetails(data)

        verify(nyReviewDetailsService).loadReviews(data)
    }
}