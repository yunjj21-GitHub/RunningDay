package com.yunjung.runningday.util

import android.util.Log
import com.yunjung.runningday.BuildConfig
import java.lang.StringBuilder

object RunningDayLog {
    const val TAG = "RDLog"

    // Verbose
    fun v(message: String){
        Log.v(TAG, buildLogMsg(message))
    }

    // Debug
    fun d(message: String){
        Log.d(TAG, buildLogMsg(message))
    }

    // Information
    fun i(message: String){
        Log.i(TAG, buildLogMsg(message))
    }

    // Warning
    fun w(message: String){
        Log.w(TAG, buildLogMsg(message))
    }

    // Error
    fun e(message: String){
        Log.e(TAG, buildLogMsg(message))
    }

    fun buildLogMsg(message: String): String{
        if(BuildConfig.DEBUG){
            return try{
                val ste = Thread.currentThread().stackTrace[4]
                val sb = StringBuilder()
                sb.append("[")
                sb.append(ste.fileName.replace(".java", "", false))
                sb.append("::")
                sb.append(ste.methodName)
                sb.append("::")
                sb.append(ste.lineNumber)
                sb.append("]")
                sb.append(message)
                sb.toString()
            } catch (e: Exception){
                ""
            }
        }
        else{
            return ""
        }
    }
}