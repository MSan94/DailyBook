package com.prj.dailybook

interface BaseView<T> {
    var presenter : T
    fun init()
}