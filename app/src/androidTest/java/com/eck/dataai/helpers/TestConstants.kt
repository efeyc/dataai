package com.eck.dataai.helpers

import com.eck.dataai.mapper.ProductMapper
import com.eck.dataai.models.api.Product
import com.eck.dataai.models.api.SharingProduct

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

    val sharingProduct = SharingProduct(PRODUCT_ID, true, MARKET)

    val product = Product(
        PRODUCT_ID, PRODUCT_NAME, PUBLISHER, CATEGORY,
        PRODUCT_DESC, ICON, ACCOUNT_ID, ACTIVE
    )

    val uiProduct = ProductMapper().map(product)
}