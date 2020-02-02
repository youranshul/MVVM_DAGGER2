package com.test.nymovie.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IoThread @Inject constructor() : ExecutionThread {
    override val scheduler: Scheduler
        get() = Schedulers.io()

}