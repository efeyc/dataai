package com.eck.dataai.network

import com.eck.dataai.data.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", "Bearer ${Constants.API_KEY}")
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
