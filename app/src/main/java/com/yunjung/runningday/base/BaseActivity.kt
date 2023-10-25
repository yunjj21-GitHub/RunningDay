package com.yunjung.runningday.base

import android.os.Bundle
import android.webkit.WebView
import androidx.fragment.app.FragmentActivity
import com.yunjung.runningday.BuildConfig

/*
* 공통 기능
* */
abstract class BaseActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        if(BuildConfig.DEBUG){
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }
}