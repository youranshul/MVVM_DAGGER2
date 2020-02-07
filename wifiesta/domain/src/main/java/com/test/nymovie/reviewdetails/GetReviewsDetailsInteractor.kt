package com.test.nymovie.reviewdetails

import com.test.nymovie.moviereviewlist.ReviewQueryData
import com.test.nymovie.rx.ExecutionThread
import com.test.nymovie.rx.PostExecutionThread
import com.test.nymovie.rx.SingleInteractor
import io.reactivex.Single
import javax.inject.Inject

class GetReviewsDetailsInteractor @Inject constructor(
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread,
    private val reviewDetailsService: ReviewDetailsService
) : SingleInteractor<ReviewDetails?, ReviewQueryData>(executionThread, postExecutionThread) {
    override fun buildUseCase(params: ReviewQueryData?): Single<ReviewDetails?> {
        params?.let {
            return reviewDetailsService.fetchDetails(it)
        } ?: kotlin.run {
            return Single.error<ReviewDetails>(NullPointerException())
        }
    }

}

class ReviewDetailsService @Inject constructor(private val service: NyReviewDetailsService) {
    fun fetchDetails(params: ReviewQueryData): Single<ReviewDetails?> {
        return service.loadReviews(params)
    }

}

interface NyReviewDetailsService {

    fun loadReviews(params: ReviewQueryData): Single<ReviewDetails?>
}
