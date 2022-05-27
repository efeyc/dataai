package com.eck.dataai.models.api

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_id") val productId: Int,
    @SerializedName("product_name") val productName: String,
    @SerializedName("publisher_name") val publisherName: String,
    @SerializedName("main_category") val mainCategory: String,
    val description: String,
    val icon: String,
    var accountId: Int?,
    var status: Boolean = false
)