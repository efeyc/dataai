package com.eck.dataai

import android.app.Application
import com.eck.dataai.di.appModule
import com.eck.dataai.di.viewModelModule
import org.koin.android.ext.koin.androidContext

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        org.koin.core.context.startKoin {
            androidContext(this@TestApp)
            modules(
                appModule(),
                viewModelModule()
            )
        }
    }
}