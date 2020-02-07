package com.test.nymovie.moviereviewlist

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.nymovie.BaseInteractorTest
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetFetchReviewsInteractorShould : BaseInteractorTest() {

    @Mock
    private lateinit var fetchReviewService: FetchReviewService
    private lateinit var interactor: GetFetchReviewsInteractor
    @Before
    fun setUp() {
        interactor =
            GetFetchReviewsInteractor(executionThread, postExecutionThread, fetchReviewService)
    }

    @Test
    fun `return movie review when call execute`() {
        callDefaults()
        val testObserver = TestObserver.create<MutableList<MovieReview>>()
        whenever(fetchReviewService.loadReviews()).thenReturn(Single.just(mutableListOf()))
        interactor.execute(testObserver)

        verify(fetchReviewService).loadReviews()
    }
}