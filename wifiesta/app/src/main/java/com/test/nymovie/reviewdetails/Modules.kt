package com.test.nymovie.reviewdetails

import androidx.lifecycle.ViewModel
import com.test.nymovie.DataMapper
import com.test.nymovie.ScreenNavigation
import com.test.nymovie.di.FragmentScoped
import com.test.nymovie.di.viewmodel.ViewModelKey
import com.test.nymovie.nymovie.reviewdetails.NyReviewDetailsGateway
import com.test.nymovie.nymovie.reviewdetails.ReviewDetailsResponse
import com.test.nymovie.nymovie.reviewdetails.ReviewDetailsResponseMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ReviewDetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeReviewDetailsFragment(): ReviewDetailsFragment


    @Binds
    @IntoMap
    @ViewModelKey(ReviewDetailsViewModel::class)
    internal abstract fun bindMovieReviewsListViewModel(viewModel: ReviewDetailsViewModel): ViewModel
}

@Module
class ReviewDetailsModuleExtended {

    @Provides
    fun providesDataMapper(responseMapper: ReviewDetailsResponseMapper): DataMapper<ReviewDetailsResponse, ReviewDetails?> =
        responseMapper

    @Provides
    fun providesNyReviewDetailsGateway(gateway: NyReviewDetailsGateway): NyReviewDetailsService =
        gateway

    @Provides
    fun providesScreenNavigator(webScreen: ReviewDetailsWebScreen): ScreenNavigation<String> =
        webScreen
}