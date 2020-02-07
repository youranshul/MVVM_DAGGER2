package com.test.nymovie.moviereviewlist

import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class FetchReviewServiceShould {

    private lateinit var nyMovieReviewService: NyMovieReviewService
    private lateinit var fetchReviewService: FetchReviewService

    @Before
    fun setUp() {
        nyMovieReviewService = Mockito.mock(NyMovieReviewService::class.java)
        fetchReviewService = FetchReviewService(nyMovieReviewService)
    }

    @Test
    fun `call loadReviews on NyMovieService`() {

        fetchReviewService.loadReviews()

        verify(nyMovieReviewService).loadReviews()
    }
}