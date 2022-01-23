package com.bertholucci.domain.di

import com.bertholucci.domain.interactor.GetTracksByName
import org.koin.dsl.module

val domainModule = module {
    factory { GetTracksByName(repository = get()) }
}