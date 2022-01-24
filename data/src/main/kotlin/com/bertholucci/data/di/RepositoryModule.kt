package com.bertholucci.data.di

import com.bertholucci.data.repository.TrackRepositoryImpl
import com.bertholucci.domain.repository.TrackRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<TrackRepository> { TrackRepositoryImpl(api = get(), dao = get()) }
}
