package com.eck.dataai.di

import com.eck.dataai.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { MainViewModel(get(), get(), get()) }
}