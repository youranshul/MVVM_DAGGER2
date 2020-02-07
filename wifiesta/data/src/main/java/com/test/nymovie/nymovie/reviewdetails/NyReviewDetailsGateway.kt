package com.test.nymovie.nymovie.reviewdetails

import com.test.nymovie.DataMapper
import com.test.nymovie.moviereviewlist.ReviewQueryData
import com.test.nymovie.nymovie.moviereviewlist.NyMovieReviewsApi
import com.test.nymovie.reviewdetails.NyReviewDetailsService
import com.test.nymovie.reviewdetails.ReviewDetails
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class NyReviewDetailsGateway @Inject constructor(
    private val retrofit: Retrofit,
    private val reviewDetailsMapper: DataMapper<ReviewDetailsResponse, ReviewDetails?>
) :
    NyReviewDetailsService {
    override fun loadReviews(params: ReviewQueryData): Single<ReviewDetails?> {
        val queryMap = hashMapOf<String, String>()
        queryMap[TITLE] = params.title
        queryMap[BYLINE] = params.reviewer
        return retrofit.create(NyMovieReviewsApi::class.java).getReviewDetails(queryMap).map {
            reviewDetailsMapper.transform(it)
        }
    }

    companion object {
        private const val TITLE = "query"
        private const val BYLINE = "reviewer"
    }

}