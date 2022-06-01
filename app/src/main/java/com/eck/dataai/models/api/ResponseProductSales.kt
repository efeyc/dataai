package com.eck.dataai.models.api

import com.google.gson.annotations.SerializedName

data class ResponseProductSales(
    @SerializedName("sales_list") val salesList: List<SalesData>,
)