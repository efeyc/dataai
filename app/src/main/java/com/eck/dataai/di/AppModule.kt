package com.eck.dataai.di

import com.eck.dataai.BuildConfig
import com.eck.dataai.data.Constants
import com.eck.dataai.managers.*
import com.eck.dataai.mapper.ProductMapper
import com.eck.dataai.network.Api
import com.eck.dataai.network.ApiInterceptor
import com.google.firebase.analytics.FirebaseAnalytics
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun appModule() = module {

    single {
        FirebaseAnalytics.getInstance(get())
    }

    single<AnalyticsManager> {
        AnalyticsManagerImpl(get())
    }

    single<LogManager> {
        LogManagerImpl()
    }

    single<DataManager> {
        DataManagerImpl(get())
    }

    single {
        ProductMapper()
    }

    single<Api> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        var builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder = builder.addInterceptor(loggingInterceptor)
        }
        val client = builder.addInterceptor(ApiInterceptor()).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        retrofit.create(Api::class.java)
    }
}