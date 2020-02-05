package com.test.nymovie.di

import com.test.nymovie.NyMovieApp
import com.test.nymovie.core.ActivityBindingModule
import com.test.nymovie.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class,
        AppModule::class,
        RetrofitModule::class
    ]
)
interface AppComponent : AndroidInjector<NyMovieApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: NyMovieApp): AppComponent
    }
}