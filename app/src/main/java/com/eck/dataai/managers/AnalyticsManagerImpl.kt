package com.eck.dataai.managers

import android.os.Bundle
import com.eck.dataai.data.AnalyticsParams
import com.google.firebase.analytics.FirebaseAnalytics

class AnalyticsManagerImpl(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsManager {

    override fun log(key: String, bundle: Bundle?) {
        firebaseAnalytics.logEvent(key, bundle)
    }

    override fun logProductEvent(key: String, productId: Int) {
        val bundle = Bundle()
        bundle.putInt(AnalyticsParams.productId, productId)
        log(key, bundle)
    }
}