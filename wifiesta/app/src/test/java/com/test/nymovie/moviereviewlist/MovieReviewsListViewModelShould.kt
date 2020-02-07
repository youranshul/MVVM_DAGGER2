package com.test.nymovie.moviereviewlist

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MovieReviewsListViewModelShould {

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var interactor: GetFetchReviewsInteractor

    @Mock
    private lateinit var data: List<MovieReview>

    @Mock
    private lateinit var navigation: ReviewDetailsScreen

    private lateinit var viewModel: MovieReviewsListViewModel

    @Before
    fun setUp() {
        viewModel = MovieReviewsListViewModel(interactor, navigation)
    }

    @Test
    fun `load reviews list from interactor`() {
        val testObserver = TestObserver.create<MutableList<MovieReview>>()
        whenever(interactor.execute(testObserver)).thenReturn(testObserver)
        val liveData = viewModel.loadMovieReviews()
        testObserver.onSuccess(data as MutableList<MovieReview>)

        liveData.value?.isNotEmpty()?.let { Assert.assertTrue(it) }
    }

    @Test
    fun `navigate to details screen when Item clicked`() {
        viewModel.onItemClick("title","reviewer")

        verify(navigation).navigateTo(any())
    }
}