package com.test.nymovie.rx

import ExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class IoThread() : ExecutionThread {
    override val scheduler: Scheduler
        get() = Schedulers.io()

}