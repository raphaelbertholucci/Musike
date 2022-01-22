package com.bertholucci.domain.di

import com.bertholucci.domain.interactor.GetMusicByName
import org.koin.dsl.module

val domainModule = module {
    factory { GetMusicByName(repository = get()) }
}