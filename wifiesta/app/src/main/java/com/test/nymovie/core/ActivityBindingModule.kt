package com.test.nymovie.core

import com.test.nymovie.di.ActivityScoped
import com.test.nymovie.moviereviewlist.MovieReviewsModule
import com.test.nymovie.moviereviewlist.movieReviewListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [movieReviewListModule::class,
            MovieReviewsModule::class]
    )
    internal abstract fun mainActivity(): MainActivity
}