package com.yunjung.runningday

import android.app.Application

class RunningDayApplication: Application() {
    companion object{
        lateinit var INSTANCE: RunningDayApplication
    }

    init{
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()

        // if (BuildConfig.DEBUG) { }
        // 그 외의 초기화 코드
    }
}