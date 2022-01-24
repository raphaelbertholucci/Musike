package com.bertholucci.domain.di

import com.bertholucci.domain.interactor.GetTrackFromDatabaseByName
import com.bertholucci.domain.interactor.GetTracksByName
import com.bertholucci.domain.interactor.InsertTrackIntoDatabase
import com.bertholucci.domain.interactor.RemoveTrackFromDatabase
import org.koin.dsl.module

val domainModule = module {
    factory { GetTracksByName(repository = get()) }
    factory { GetTrackFromDatabaseByName(repository = get()) }
    factory { InsertTrackIntoDatabase(repository = get()) }
    factory { RemoveTrackFromDatabase(repository = get()) }
}