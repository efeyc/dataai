package com.eck.dataai.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(coroutineContext: CoroutineContext) : ViewModel() {
    private val parentJob = Job()

    protected val scope = CoroutineScope(parentJob + coroutineContext)

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}