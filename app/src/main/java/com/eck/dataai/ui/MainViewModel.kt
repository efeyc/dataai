package com.eck.dataai.ui

import com.eck.dataai.base.BaseViewModel
import com.eck.dataai.managers.DataManager
import com.eck.dataai.managers.LogManager
import com.eck.dataai.network.Api
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val dataManager: DataManager,
    private val api: Api,
    private val logManager: LogManager,
    coroutineContext: CoroutineContext = Dispatchers.Default
) : BaseViewModel(coroutineContext) {


}