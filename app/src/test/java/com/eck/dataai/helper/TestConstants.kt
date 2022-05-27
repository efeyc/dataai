package com.eck.dataai.helper

import com.eck.dataai.models.api.Product
import com.eck.dataai.models.api.SharingProduct
import com.eck.dataai.models.ui.UIProduct

object TestConstants {

    const val ACCOUNT_ID = 1
    const val PRODUCT_ID = 12345678
    const val MARKET = "ios"
    const val ACTIVE = true
    const val INACTIVE = false

    val sharingProduct = SharingProduct(PRODUCT_ID, true, MARKET)

    val product = Product(
        PRODUCT_ID, "name",
        "publisher", "category",
        "description", "icon", ACCOUNT_ID, ACTIVE
    )

    val uiProduct = UIProduct(
        "name",
        "publisher", "category",
        "description", "icon", ACCOUNT_ID, ACTIVE
    )


}