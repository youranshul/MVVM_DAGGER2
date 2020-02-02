package com.test.nymovie.moviereviewlist

import com.test.nymovie.DataMapper
import com.test.nymovie.nymovie.moviereviewlist.MovieReviewResponse
import com.test.nymovie.nymovie.moviereviewlist.MovieReviewsResponseMapper
import com.test.nymovie.nymovie.moviereviewlist.NyMovieReviewsGateway
import dagger.Module
import dagger.Provides

@Module
class MovieReviewsModule {

    @Provides
    fun providesNyMoviewReviewService(nyMovieReviewsGateway: NyMovieReviewsGateway): NyMovieReviewService =
        nyMovieReviewsGateway

    @Provides
    fun providesDataMapper(mapper: MovieReviewsResponseMapper): DataMapper<MovieReviewResponse, MutableList<MovieReview>> =
        mapper
}