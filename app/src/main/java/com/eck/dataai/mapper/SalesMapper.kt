package com.eck.dataai.mapper

import com.eck.dataai.models.api.SalesData
import com.eck.dataai.models.ui.UISales

interface SalesMapper {
    fun map(value: SalesData): UISales
}