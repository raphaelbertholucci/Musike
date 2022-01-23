package com.bertholucci.search.ui

import android.view.LayoutInflater
import com.bertholucci.common.base.BaseActivity
import com.bertholucci.search.databinding.SearchActivityHomeBinding

class HomeActivity : BaseActivity<SearchActivityHomeBinding>() {
    override fun getViewBinding() = SearchActivityHomeBinding.inflate(LayoutInflater.from(this))
}