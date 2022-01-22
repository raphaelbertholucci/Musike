package com.bertholucci.musike.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bertholucci.common.base.BaseActivity
import com.bertholucci.common.intentToSearch
import com.bertholucci.musike.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(::navigateToHome, 5000)
    }

    private fun navigateToHome() {
        startActivity(intentToSearch())
    }
}