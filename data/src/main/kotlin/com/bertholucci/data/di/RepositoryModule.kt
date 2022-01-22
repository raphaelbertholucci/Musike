package com.bertholucci.data.di

import com.bertholucci.data.repository.MusicRepositoryImpl
import com.bertholucci.domain.repository.MusicRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<MusicRepository> { MusicRepositoryImpl(api = get()) }
}
