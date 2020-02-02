package com.test.nymovie.nymovie.moviereviewlist

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitService @Inject constructor() {

    private val retrofit: Retrofit

    companion object {
        private val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerKotlinModule()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getNyMovieService(): NyMoviesService {
        return retrofit.create(NyMoviesService::class.java)
    }

}

interface NyMoviesService {

    @GET("reviews/search.json")
    fun getReviews(@Query("api-key") api_key: String = "prg8vxmQrjJPSqPpVIWOcxiZKjwJ4UIe"): Single<MovieReviewResponse>
}

