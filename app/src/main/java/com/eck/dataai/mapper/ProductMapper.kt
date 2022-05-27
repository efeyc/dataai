package com.eck.dataai.mapper

import com.eck.dataai.models.api.Product
import com.eck.dataai.models.ui.UIProduct

interface ProductMapper {
    fun map(value: Product): UIProduct
}