package com.bertholucci.search.di

import com.bertholucci.search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { SearchViewModel(get()) }
}