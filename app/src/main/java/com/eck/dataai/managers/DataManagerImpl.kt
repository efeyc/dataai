package com.eck.dataai.managers

import com.eck.dataai.models.api.Product
import com.eck.dataai.models.api.SalesData
import com.eck.dataai.network.Api

class DataManagerImpl(private val api: Api) : DataManager {

    override suspend fun getUserProducts(): List<Product> {

        val result = api.getSharingProducts()
        val products = ArrayList<Product>()
        val sharing = result.sharings.first()
        val activeProducts = sharing.products //.filter { it.status }
        activeProducts.forEach { sharingProduct ->
            val product = api.getProductDetail(sharingProduct.market, sharingProduct.productId).product
            product.accountId = sharing.accountId
            product.status = sharingProduct.status
            products.add(product)
        }
        return products
    }

    override suspend fun getProductSales(accountId: Int, productId: Int): List<SalesData> {
        val result = api.getProductSales(accountId, productId)
        return result.salesList.sortedByDescending { it.units.product.downloads }
    }
}