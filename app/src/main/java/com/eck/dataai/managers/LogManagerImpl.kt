package com.eck.dataai.managers

import android.util.Log
import com.eck.dataai.BuildConfig

class LogManagerImpl : LogManager {

    private val tag = "DataAI"

    override fun log(message: String?) {
        log(tag, message)
    }

    override fun log(tag: String, message: String?) {
        if (BuildConfig.DEBUG) {
            message?.let {
                Log.d(tag, it)
            }
        }
    }

    override fun logError(throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, "Error: ", throwable)
        }
    }

    override fun logError(tag: String, message: String?) {
        if (BuildConfig.DEBUG) {
            message?.let {
                Log.e(tag, it)
            }
        }
    }

}