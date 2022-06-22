package com.bertholucci.search.di

import com.bertholucci.search.model.Track
import com.bertholucci.search.ui.SearchViewModel
import com.bertholucci.search.ui.details.TrackDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { (track: Track) -> TrackDetailsViewModel(get(), get(), get(), track) }
}
