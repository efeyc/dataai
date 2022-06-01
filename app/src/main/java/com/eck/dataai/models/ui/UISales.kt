package com.eck.dataai.models.ui

import com.eck.dataai.R
import com.eck.dataai.models.api.RevenueData
import com.eck.dataai.models.api.UnitsData
import com.eck.dataai.ui.MainViewModel
import com.eck.dataai.ui.common.ItemViewModel

class UISales(
    val country: String,
    val units: UnitsData,
    val revenue: RevenueData
) : ItemViewModel {
    override val layoutId: Int = R.layout.item_sales
    override val viewType: Int = MainViewModel.SALES_ITEM
}