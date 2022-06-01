package com.eck.dataai.managers

import com.eck.dataai.helper.TestConstants.ACCOUNT_ID
import com.eck.dataai.helper.TestConstants.PRODUCT_ID
import com.eck.dataai.helper.TestConstants.product
import com.eck.dataai.helper.TestConstants.salesData
import com.eck.dataai.helper.TestConstants.sharingProduct
import com.eck.dataai.models.api.ResponseProductDetail
import com.eck.dataai.models.api.ResponseProductSales
import com.eck.dataai.models.api.ResponseSharingProducts
import com.eck.dataai.models.api.Sharing
import com.eck.dataai.network.Api
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class DataManagerImplTest {

    @Mock
    lateinit var api: Api

    private val sharing = Sharing(ACCOUNT_ID, listOf(sharingProduct))
    private val responseSharingProducts = ResponseSharingProducts(listOf(sharing))
    private val responseProductDetail = ResponseProductDetail(product)
    private val responseProductSales = ResponseProductSales(listOf(salesData))

    private lateinit var dataManager: DataManager

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dataManager = DataManagerImpl(api)
    }

    @Test
    fun getUserProducts() = runBlocking {
        `when`(api.getSharingProducts()).thenReturn(responseSharingProducts)
        `when`(api.getProductDetail(sharingProduct.market, sharingProduct.productId)).thenReturn(responseProductDetail)

        val result = dataManager.getUserProducts()

        verify(api).getSharingProducts()
        verify(api).getProductDetail(anyString(), anyInt())
        assertEquals(listOf(responseProductDetail.product), result)
    }

    @Test
    fun getProductSales() = runBlocking {
        `when`(api.getProductSales(ACCOUNT_ID, PRODUCT_ID)).thenReturn(responseProductSales)

        val result = dataManager.getProductSales(ACCOUNT_ID, PRODUCT_ID)

        verify(api).getProductSales(ACCOUNT_ID, PRODUCT_ID)
        assertEquals(responseProductSales.salesList, result)
    }

}