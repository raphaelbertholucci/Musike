package com.bertholucci.musike.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.bertholucci.common.base.BaseActivity
import com.bertholucci.common.intentToSearch
import com.bertholucci.musike.databinding.ActivitySplashBinding

class LauncherActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(LayoutInflater.from(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(::navigateToHome, 4000)
    }

    private fun navigateToHome() {
        startActivity(intentToSearch())
    }
}