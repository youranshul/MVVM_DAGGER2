package com.test.nymovie.moviereviewlist


import com.test.nymovie.rx.ExecutionThread
import com.test.nymovie.rx.PostExecutionThread
import com.test.nymovie.rx.SingleInteractor
import io.reactivex.Single
import javax.inject.Inject


class GetFetchReviewsInteractor @Inject constructor(
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread,
    private val fetchReviewsService: FetchReviewService
) : SingleInteractor<MutableList<MovieReview>, Unit>(executionThread, postExecutionThread) {
    override fun buildUseCase(params: Unit?): Single<MutableList<MovieReview>> {
        return fetchReviewsService.loadReviews()
    }

}

class FetchReviewService @Inject constructor(private val nyMovieReviewService: NyMovieReviewService) {

    fun loadReviews(): Single<MutableList<MovieReview>> {
        return nyMovieReviewService.loadReviews()
    }

}
