package com.test.nymovie.di


import com.test.nymovie.rx.ExecutionThread
import com.test.nymovie.rx.IoThread
import com.test.nymovie.rx.PostExecutionThread
import com.test.nymovie.rx.UiThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideIoThread(ioThread: IoThread): ExecutionThread = ioThread

    @Singleton
    @Provides
    fun provideMainThread(uiThread: UiThread): PostExecutionThread = uiThread

}