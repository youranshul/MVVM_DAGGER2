package com.test.nymovie.moviereviewlist

import androidx.lifecycle.ViewModel
import com.test.nymovie.di.FragmentScoped
import com.test.nymovie.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class movieReviewListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMovieFragment(): MovieReviewFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieReviewsListViewModel::class)
    internal abstract fun bindMovieReviewsListViewModel(viewModel: MovieReviewsListViewModel): ViewModel

}