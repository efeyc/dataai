package com.eck.dataai.ui

import com.eck.dataai.helper.InstantExecutorExtension
import com.eck.dataai.helper.TestConstants.product
import com.eck.dataai.helper.TestConstants.uiProduct
import com.eck.dataai.managers.DataManager
import com.eck.dataai.managers.LogManager
import com.eck.dataai.mapper.Mapper
import com.eck.dataai.models.api.Product
import com.eck.dataai.models.ui.UIProduct
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
    lateinit var productMapper: Mapper<Product, UIProduct>

    private val dispatcher = UnconfinedTestDispatcher()
    private val exception = RuntimeException("test")

    private lateinit var mainViewModel: MainViewModel

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadData(): Unit = runTest {
        `when`(dataManager.getUserProducts()).thenReturn(listOf(product))
        `when`(productMapper.map(product)).thenReturn(uiProduct)
        mainViewModel = MainViewModel(dataManager, logManager, productMapper)

        verify(dataManager).getUserProducts()
        verify(productMapper).map(product)
        verify(logManager, never()).logError(exception)
        assertEquals(listOf(uiProduct), mainViewModel.data.value)
    }
}