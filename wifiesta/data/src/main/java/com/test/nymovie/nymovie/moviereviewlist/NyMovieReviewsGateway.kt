package com.test.nymovie.nymovie.moviereviewlist

import com.test.nymovie.DataMapper
import com.test.nymovie.moviereviewlist.MovieReview
import com.test.nymovie.moviereviewlist.NyMovieReviewService
import io.reactivex.Single
import javax.inject.Inject

class NyMovieReviewsGateway @Inject constructor(
    private val retrofitService: RetrofitService,
    private val movieReviewsResponseMapper: DataMapper<MovieReviewResponse, MutableList<MovieReview>>
) :
    NyMovieReviewService {

    override fun loadReviews(): Single<MutableList<MovieReview>> {
        return retrofitService.getNyMovieService().getReviews().map {
            movieReviewsResponseMapper.transform(it)
        }.map {
            it.sortBy { model ->
                model.title
            }
            return@map it
        }
    }


}