package com.eck.dataai.ui

sealed class ProductListEvent {
    data class ShowSelectedProduct(val accountId: Int, val productId: Int) : ProductListEvent()
}