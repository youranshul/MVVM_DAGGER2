package com.test.nymovie.reviewdetails

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.test.nymovie.BaseInteractorTest
import com.test.nymovie.moviereviewlist.ReviewQueryData
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class GetReviewsDetailsInteractorShould : BaseInteractorTest() {


    @Mock
    private lateinit var reviewDetailsService: ReviewDetailsService


    private lateinit var reviewsDetailsInteractor: GetReviewsDetailsInteractor
    @Before
    fun setUp() {
        reviewsDetailsInteractor =
            GetReviewsDetailsInteractor(executionThread, postExecutionThread, reviewDetailsService)
        callDefaults()
    }

    @Test
    fun `not call fetchDetails when data is null`() {
        val testObserver = TestObserver.create<ReviewDetails>()
        reviewsDetailsInteractor.execute(testObserver, null)

        verifyZeroInteractions(reviewDetailsService)
    }

    @Test
    fun `call fetchDetails  when data is valid`() {
        val returnData = Mockito.mock(ReviewDetails::class.java)
        whenever(reviewDetailsService.fetchDetails(any())).thenReturn(Single.just(returnData))
        val testObserver = TestObserver.create<ReviewDetails>()
        val data = Mockito.mock(ReviewQueryData::class.java)
        reviewsDetailsInteractor.execute(testObserver, data)

        verify(reviewDetailsService).fetchDetails(data)
    }
}