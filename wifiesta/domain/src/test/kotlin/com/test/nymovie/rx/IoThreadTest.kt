package com.test.nymovie.rx

import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

internal class IoThreadShould {


    private val ioThread = IoThread()

    @Test
    fun `return io thread`() {
        Assert.assertThat(ioThread.scheduler, CoreMatchers.instanceOf(Schedulers.io()::class.java))
    }
}