package com.eck.dataai.mapper

import com.eck.dataai.models.api.SalesData
import com.eck.dataai.models.ui.UISales

class SalesMapperImpl : SalesMapper {

    override fun map(value: SalesData): UISales {
        return UISales(value.country, value.units, value.revenue)
    }
}