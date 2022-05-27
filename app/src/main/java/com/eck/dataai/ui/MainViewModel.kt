package com.eck.dataai.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eck.dataai.managers.DataManager
import com.eck.dataai.managers.LogManager
import com.eck.dataai.mapper.Mapper
import com.eck.dataai.mapper.ProductMapper
import com.eck.dataai.models.api.Product
import com.eck.dataai.models.ui.UIProduct
import com.eck.dataai.ui.common.ItemViewModel
import kotlinx.coroutines.launch

open class MainViewModel(
    private val dataManager: DataManager,
    private val logManager: LogManager,
    private val productMapper: Mapper<Product, UIProduct>
) : ViewModel() {

    val data: LiveData<List<ItemViewModel>>
        get() = _data
    private val _data = MutableLiveData<List<ItemViewModel>>()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                val userProducts = dataManager.getUserProducts()
                val viewData = userProducts.map { productMapper.map(it) }
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