package com.test.nymovie.nymovie.moviereviewlist

import com.test.nymovie.DataMapper
import com.test.nymovie.moviereviewlist.MovieReview
import com.test.nymovie.moviereviewlist.NyMovieReviewService
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class NyMovieReviewsGateway @Inject constructor(
    private val retrofit: Retrofit,
    private val movieReviewsResponseMapper: DataMapper<MovieReviewResponse, MutableList<MovieReview>>
) :
    NyMovieReviewService {

    override fun loadReviews(): Single<MutableList<MovieReview>> {
        return retrofit.create(NyMovieReviewsApi::class.java).getReviews().map {
            movieReviewsResponseMapper.transform(it)
        }.map {
            it.sortBy { model ->
                model.title
            }
            return@map it
        }
    }


}