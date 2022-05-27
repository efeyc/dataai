package com.eck.dataai.mapper

import com.eck.dataai.models.api.Product
import com.eck.dataai.models.ui.UIProduct

class ProductMapperImpl : ProductMapper {

    override fun map(value: Product): UIProduct {
        return UIProduct(
            value.productId,
            value.productName, value.publisherName, value.mainCategory,
            value.description, value.icon, value.accountId, value.status
        )
    }
}