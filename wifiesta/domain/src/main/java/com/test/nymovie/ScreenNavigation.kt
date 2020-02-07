package com.test.nymovie

interface ScreenNavigation<T> {
    fun navigateTo(data: T)
}