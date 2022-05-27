package com.eck.dataai.network

import com.eck.dataai.models.api.ResponseProductDetail
import com.eck.dataai.models.api.ResponseProductSales
import com.eck.dataai.models.api.ResponseSharingProducts
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("sharing/products")
    suspend fun getSharingProducts(): ResponseSharingProducts

    @GET("apps/{market}/app/{productId}/details")
    suspend fun getProductDetail(@Path("market") market: String, @Path("productId") productId: Int): ResponseProductDetail

    @GET("accounts/{accountId}/products/{productId}/sales?break_down=country")
    suspend fun getProductSales(@Path("accountId") accountId: Int, @Path("productId") productId: Int): ResponseProductSales
}
