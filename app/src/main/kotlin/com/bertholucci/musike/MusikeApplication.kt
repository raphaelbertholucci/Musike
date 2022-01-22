package com.bertholucci.musike

import android.app.Application
import com.bertholucci.data.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MusikeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MusikeApplication)
            androidLogger()
            modules(listOf(apiModule))
        }
    }
}