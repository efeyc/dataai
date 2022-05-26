package com.eck.dataai

import android.app.Application
import com.eck.dataai.BuildConfig
import com.eck.dataai.di.appModule
import com.eck.dataai.di.viewModelModule
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
        }

        startKoin {
            androidContext(this@App)
            modules(
                appModule(),
                viewModelModule()
            )
        }
    }
}