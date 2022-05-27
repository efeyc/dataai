package com.eck.dataai.helpers

import com.eck.dataai.mapper.ProductMapperImpl
import com.eck.dataai.mapper.SalesMapperImpl
import com.eck.dataai.models.api.*

object TestConstants {

    const val ACCOUNT_ID = 1
    const val PRODUCT_ID = 12345678
    const val MARKET = "ios"
    const val ACTIVE = true
    const val INACTIVE = false
    const val PRODUCT_NAME = "productName"
    const val PRODUCT_DESC = "productDesc"
    const val PUBLISHER = "publisher"
    const val CATEGORY = "category"
    const val ICON = "url"
    const val COUNTRY = "NL"
    const val DOWNLOADS = 100
    const val UPDATES = 50
    const val SALES = 80
    const val REFUNDS = 2

    val sharingProduct = SharingProduct(PRODUCT_ID, true, MARKET)

    val product = Product(
        PRODUCT_ID, PRODUCT_NAME, PUBLISHER, CATEGORY,
        PRODUCT_DESC, ICON, ACCOUNT_ID, ACTIVE
    )

    val unitsProductDate = UnitsProductData(0, DOWNLOADS, UPDATES, 0)
    val unitsIapData = UnitsIapData(0, SALES, REFUNDS)
    val unitsData = UnitsData(unitsProductDate, unitsIapData)

    val revenueProductDate = RevenueProductData(0.0, DOWNLOADS.toDouble(), UPDATES.toDouble(), 0.0)
    val revenueIapData = RevenueIapData(0.0, SALES.toDouble(), REFUNDS.toDouble())
    val revenueData = RevenueData(revenueProductDate, revenueIapData, 0.0)

    val salesData = SalesData(COUNTRY, unitsData, revenueData)
    val uiProduct = ProductMapperImpl().map(product)
    val uiSales = SalesMapperImpl().map(salesData)

}