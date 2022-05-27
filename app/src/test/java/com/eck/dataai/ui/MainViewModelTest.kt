package com.eck.dataai.ui

import com.eck.dataai.helper.InstantExecutorExtension
import com.eck.dataai.helper.TestConstants.ACCOUNT_ID
import com.eck.dataai.helper.TestConstants.PRODUCT_ID
import com.eck.dataai.helper.TestConstants.product
import com.eck.dataai.helper.TestConstants.salesData
import com.eck.dataai.helper.TestConstants.uiProduct
import com.eck.dataai.helper.TestConstants.uiSales
import com.eck.dataai.managers.AnalyticsManager
import com.eck.dataai.managers.DataManager
import com.eck.dataai.managers.LogManager
import com.eck.dataai.mapper.ProductMapper
import com.eck.dataai.mapper.SalesMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExtendWith(InstantExecutorExtension::class)
@ExperimentalCoroutinesApi
class MainViewModelTest {

    @Mock
    lateinit var dataManager: DataManager

    @Mock
    lateinit var logManager: LogManager

    @Mock
    lateinit var analyticsManager: AnalyticsManager

    @Mock
    lateinit var salesMapper: SalesMapper

    @Mock
    lateinit var productMapper: ProductMapper

    private val dispatcher = UnconfinedTestDispatcher()
    private val exception = RuntimeException("test")

    private lateinit var mainViewModel: MainViewModel

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        `when`(productMapper.map(product)).thenReturn(uiProduct)
        `when`(salesMapper.map(salesData)).thenReturn(uiSales)

        mainViewModel = MainViewModel(dataManager, logManager, productMapper, salesMapper, analyticsManager)

    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadData(): Unit = runTest {
        `when`(dataManager.getUserProducts()).thenReturn(listOf(product))
        mainViewModel.loadData()

        verify(dataManager).getUserProducts()
        verify(logManager, never()).logError(exception)
        assertEquals(listOf(uiProduct), mainViewModel.data.value)
    }

    @Test
    fun loadProductSales(): Unit = runTest {
        `when`(dataManager.getProductSales(ACCOUNT_ID, PRODUCT_ID)).thenReturn(listOf(salesData))
        mainViewModel.loadProductSales(ACCOUNT_ID, PRODUCT_ID)

        verify(dataManager).getProductSales(ACCOUNT_ID, PRODUCT_ID)
        verify(logManager, never()).logError(exception)
        assertEquals(listOf(uiSales), mainViewModel.productSales.value)
    }
}