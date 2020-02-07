package com.test.nymovie

import com.nhaarman.mockitokotlin2.whenever
import com.test.nymovie.rx.ExecutionThread
import com.test.nymovie.rx.PostExecutionThread
import io.reactivex.schedulers.TestScheduler
import org.junit.Rule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

open class BaseInteractorTest {

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var executionThread: ExecutionThread

    @Mock
    lateinit var postExecutionThread: PostExecutionThread


    protected fun callDefaults() {
        whenever(executionThread.scheduler).thenReturn(TestScheduler())
        whenever(postExecutionThread.scheduler).thenReturn(TestScheduler())
    }
}