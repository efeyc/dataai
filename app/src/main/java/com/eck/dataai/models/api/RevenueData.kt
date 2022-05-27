package com.eck.dataai.models.api

data class RevenueData(
    val product: RevenueProductData,
    val iap: RevenueIapData,
    val ad: Double
)