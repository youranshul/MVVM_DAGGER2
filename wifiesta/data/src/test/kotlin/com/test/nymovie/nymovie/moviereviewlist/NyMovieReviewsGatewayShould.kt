package com.test.nymovie.nymovie.moviereviewlist

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.nymovie.moviereviewlist.MovieReview
import io.reactivex.Single
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Retrofit

class NyMovieReviewsGatewayShould {

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var retrofit: Retrofit

    @Mock
    private lateinit var api: NyMovieReviewsApi

    @Mock
    private lateinit var dataMapper: MovieReviewsResponseMapper

    @Mock
    private lateinit var response: MovieReviewResponse

    @Mock
    private lateinit var transformedData: MutableList<MovieReview>


    private lateinit var nyMovieReviewsGateway: NyMovieReviewsGateway
    @Before
    fun setUp() {
        nyMovieReviewsGateway = NyMovieReviewsGateway(retrofit, dataMapper)
    }

    @Test
    fun `call getReviews api`() {
        whenever(retrofit.create(NyMovieReviewsApi::class.java)).thenReturn(api)
        whenever(api.getReviews()).thenReturn(Single.just(response))
        nyMovieReviewsGateway.loadReviews()

        verify(api).getReviews()
    }

    @Test
    fun `call getReviews api and return transformed data`() {
        whenever(retrofit.create(NyMovieReviewsApi::class.java)).thenReturn(api)
        whenever(dataMapper.transform(response)).thenReturn(transformedData)
        whenever(api.getReviews()).thenReturn(Single.just(response))
        val data = nyMovieReviewsGateway.loadReviews()

        Assert.assertThat(transformedData, CoreMatchers.sameInstance(data.blockingGet()))
    }
}