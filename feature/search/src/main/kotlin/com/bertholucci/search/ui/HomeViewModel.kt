package com.bertholucci.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _loadingSplash = MutableStateFlow(true)
    val loadingSplash = _loadingSplash.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _loadingSplash.value = false
        }
    }
}