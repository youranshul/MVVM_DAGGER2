package com.test.nymovie.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class UiThreadShould {
    private val uiThread = UiThread()

    @Test
    fun `return post execution thread thread`() {
        Assert.assertThat(
            uiThread.scheduler,
            CoreMatchers.instanceOf(AndroidSchedulers.mainThread()::class.java)
        )
    }
}