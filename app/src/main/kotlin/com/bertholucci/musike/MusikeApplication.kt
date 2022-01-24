package com.bertholucci.musike

import android.app.Application
import com.bertholucci.data.di.apiModule
import com.bertholucci.data.di.databaseModule
import com.bertholucci.data.di.repositoryModule
import com.bertholucci.domain.di.domainModule
import com.bertholucci.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MusikeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MusikeApplication)
            androidLogger()
            modules(listOf(apiModule, repositoryModule, domainModule, searchModule, databaseModule))
        }
    }
}