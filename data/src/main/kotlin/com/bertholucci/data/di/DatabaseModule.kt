package com.bertholucci.data.di

import android.app.Application
import androidx.room.Room
import com.bertholucci.data.database.MusikeDao
import com.bertholucci.data.database.MusikeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val DATABASE_NAME = "musike-db"

val databaseModule = module {

    fun provideDatabase(application: Application): MusikeDatabase {
        return Room.databaseBuilder(application, MusikeDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMusikeDao(database: MusikeDatabase): MusikeDao {
        return database.musikeDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideMusikeDao(get()) }
}