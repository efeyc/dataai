package com.eck.dataai.managers

import android.os.Bundle

interface AnalyticsManager {

    fun log(key: String, bundle: Bundle? = null)

}