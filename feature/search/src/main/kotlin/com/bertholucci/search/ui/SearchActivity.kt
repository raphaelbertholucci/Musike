package com.bertholucci.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import com.bertholucci.common.base.BaseActivity
import com.bertholucci.search.databinding.SearchActivityMainBinding

class SearchActivity : BaseActivity<SearchActivityMainBinding>() {

    override fun getViewBinding() = SearchActivityMainBinding.inflate(LayoutInflater.from(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addObservers()
        addListeners()
    }

    private fun addListeners() {

    }

    private fun addObservers() {

    }
}