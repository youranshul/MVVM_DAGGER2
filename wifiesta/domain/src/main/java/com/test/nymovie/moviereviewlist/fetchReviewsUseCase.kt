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
) : SingleInteractor<List<MovieReview>, Unit>(executionThread, postExecutionThread) {
    override fun buildUseCase(params: Unit?): Single<List<MovieReview>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class FetchReviewService @Inject constructor() {

}
