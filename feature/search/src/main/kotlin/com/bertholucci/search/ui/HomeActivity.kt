package com.bertholucci.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bertholucci.common.base.BaseActivity
import com.bertholucci.search.databinding.SearchActivityHomeBinding

class HomeActivity : BaseActivity<SearchActivityHomeBinding>() {

    override fun getViewBinding() = SearchActivityHomeBinding.inflate(LayoutInflater.from(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
    }
}
