package com.eck.dataai.mapper

import com.eck.dataai.models.api.SalesData
import com.eck.dataai.models.ui.UISales

class SalesMapper : Mapper<SalesData, UISales> {

    override fun map(value: SalesData): UISales {
        return UISales(value.country, value.units, value.revenue)
    }
}