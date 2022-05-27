package com.eck.dataai.models.ui

import com.eck.dataai.R
import com.eck.dataai.ui.MainViewModel
import com.eck.dataai.ui.common.ItemViewModel

class UIProduct(
    val productName: String,
    val publisherName: String,
    val mainCategory: String,
    val description: String,
    val icon: String,
    val accountId: Int?,
    var status: Boolean = false
) : ItemViewModel {
    override val layoutId: Int = R.layout.item_product
    override val viewType: Int = MainViewModel.PRODUCT_ITEM
}