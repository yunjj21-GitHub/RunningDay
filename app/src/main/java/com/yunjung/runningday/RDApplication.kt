package com.yunjung.runningday

import android.app.Application

class RDApplication: Application() {
    companion object{
        lateinit var INSTANCE: RDApplication

        fun getApp(): RDApplication{
            return INSTANCE
        }
    }

    init{
        INSTANCE = this
    }
}