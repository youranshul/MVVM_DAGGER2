package com.test.nymovie


interface DataMapper<RESPONSE, MODEL> {
    fun transform(data: RESPONSE): MODEL
}