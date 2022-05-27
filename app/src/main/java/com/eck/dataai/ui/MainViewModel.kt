package com.eck.dataai.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eck.dataai.data.AnalyticsKeys
import com.eck.dataai.managers.AnalyticsManager
import com.eck.dataai.managers.DataManager
import com.eck.dataai.managers.LogManager
import com.eck.dataai.mapper.Mapper
import com.eck.dataai.models.api.Product
import com.eck.dataai.models.ui.UIProduct
import com.eck.dataai.testutils.OpenForTesting
import com.eck.dataai.ui.common.Event
import com.eck.dataai.ui.common.ItemViewModel
import kotlinx.coroutines.launch

@OpenForTesting
open class MainViewModel(
    private val dataManager: DataManager,
    private val logManager: LogManager,
    private val productMapper: Mapper<Product, UIProduct>,
    private val analyticsManager: AnalyticsManager
) : ViewModel() {

    val data: LiveData<List<ItemViewModel>>
        get() = _data
    private val _data = MutableLiveData<List<ItemViewModel>>()

    val events: LiveData<Event<ProductListEvent>>
        get() = _events
    private val _events = MutableLiveData<Event<ProductListEvent>>()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                val userProducts = dataManager.getUserProducts()
                val viewData = userProducts.map {
                    val uiProduct = productMapper.map(it)
                    uiProduct.onItemClick = { accountId, productId ->
                        accountId?.let { accId ->
                            _events.postValue(Event(ProductListEvent.ShowSelectedProduct(accId, productId)))
                        }
                        analyticsManager.logProductEvent(AnalyticsKeys.mainProductClicked, productId)
                    }
                    uiProduct
                }
                _data.postValue(viewData)
            } catch (exc: Exception) {
                logManager.logError(exc)
            }
        }
    }

    companion object {
        const val PRODUCT_ITEM = 1
    }
}