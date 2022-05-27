package com.eck.dataai.models.api

import com.google.gson.annotations.SerializedName

data class Sharing(
    @SerializedName("owner_account_id") val accountId: Int,
    val products: List<SharingProduct>
)