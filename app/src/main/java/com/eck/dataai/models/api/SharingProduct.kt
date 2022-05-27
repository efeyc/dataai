package com.eck.dataai.models.api

import com.google.gson.annotations.SerializedName

data class SharingProduct(
    @SerializedName("product_id") val productId: Int,
    val status: Boolean,
    val market: String
)