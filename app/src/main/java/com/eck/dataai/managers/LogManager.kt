package com.eck.dataai.managers

interface LogManager {

    fun log(message: String?)

    fun log(tag: String, message: String?)

    fun logError(throwable: Throwable)

    fun logError(tag: String, message: String?)

}