package com.test.nymovie.nymovie.reviewdetails

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.nymovie.moviereviewlist.ReviewQueryData
import com.test.nymovie.nymovie.moviereviewlist.NyMovieReviewsApi
import com.test.nymovie.reviewdetails.ReviewDetails
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

class NyReviewDetailsGatewayShould {
    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var retrofit: Retrofit

    @Mock
    private lateinit var api: NyMovieReviewsApi

    @Mock
    private lateinit var dataMapper: ReviewDetailsResponseMapper

    @Mock
    private lateinit var request: ReviewQueryData

    @Mock
    private lateinit var response: ReviewDetailsResponse

    @Mock
    private lateinit var transformedData: ReviewDetails


    private lateinit var nyReviewDetailsGateway: NyReviewDetailsGateway

    @Before
    fun setUp() {
        nyReviewDetailsGateway = NyReviewDetailsGateway(retrofit, dataMapper)
    }

    @Test
    fun `call review details api when passing request as query params`() {
        whenever(retrofit.create(NyMovieReviewsApi::class.java)).thenReturn(api)
        whenever(api.getReviewDetails(any())).thenReturn(Single.just(response))

        nyReviewDetailsGateway.loadReviews(request)

        verify(api).getReviewDetails(any())
    }

    @Test
    fun `call review details api and return the transformed data`() {
        whenever(retrofit.create(NyMovieReviewsApi::class.java)).thenReturn(api)
        whenever(api.getReviewDetails(any())).thenReturn(Single.just(response))
        whenever(dataMapper.transform(response)).thenReturn(transformedData)

        val single = nyReviewDetailsGateway.loadReviews(request)

        Assert.assertThat(transformedData, CoreMatchers.sameInstance(single.blockingGet()))
    }

}