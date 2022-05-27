package com.eck.dataai.mapper

import com.eck.dataai.models.api.Product
import com.eck.dataai.models.ui.UIProduct

class ProductMapper : Mapper<Product, UIProduct> {

    override fun map(value: Product): UIProduct {
        return UIProduct(
            value.productName, value.publisherName, value.mainCategory,
            value.description, value.icon, value.accountId, value.status
        )
    }
}