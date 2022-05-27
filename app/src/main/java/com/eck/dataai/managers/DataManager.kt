package com.eck.dataai.managers

import com.eck.dataai.models.api.Product

interface DataManager {

    suspend fun getUserProducts(): List<Product>
}