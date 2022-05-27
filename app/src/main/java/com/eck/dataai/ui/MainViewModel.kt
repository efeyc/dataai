package com.eck.dataai.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eck.dataai.managers.DataManager
import com.eck.dataai.managers.LogManager
import com.eck.dataai.models.ui.UIProduct
import com.eck.dataai.ui.common.ItemViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataManager: DataManager,
    private val logManager: LogManager
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
                val viewData =
                    userProducts.map {
                        UIProduct(
                            it.productName, it.publisherName, it.mainCategory,
                            it.description, it.icon, it.accountId, it.status
                        )
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