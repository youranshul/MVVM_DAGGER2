package com.test.nymovie.di

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.test.nymovie.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerKotlinModule()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder().addInterceptor {
            val chainRequest = it.request()

            val originalUrl = chainRequest.url()

            val httpsUrl =
                originalUrl.newBuilder()
                    .addQueryParameter("api-key", BuildConfig.API_KEY).build()

            val requestBuilder = chainRequest.newBuilder()
                .url(httpsUrl)
            val request = requestBuilder.build()

            return@addInterceptor it.proceed(request)

        }.build()
        return okHttpClient
    }
}