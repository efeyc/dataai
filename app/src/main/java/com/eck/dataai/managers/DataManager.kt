package com.eck.dataai.managers

import com.eck.dataai.models.api.Product
import com.eck.dataai.models.api.SalesData

interface DataManager {

    suspend fun getUserProducts(): List<Product>

    suspend fun getProductSales(accountId: Int, productId: Int): List<SalesData>
}