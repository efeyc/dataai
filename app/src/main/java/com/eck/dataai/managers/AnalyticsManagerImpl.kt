package com.eck.dataai.managers

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class AnalyticsManagerImpl(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsManager {

    override fun log(key: String, bundle: Bundle?) {
        firebaseAnalytics.logEvent(key, bundle)
    }
}