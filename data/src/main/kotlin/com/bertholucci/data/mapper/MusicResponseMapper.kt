package com.bertholucci.data.mapper

import com.bertholucci.common.base.BaseMapper
import com.bertholucci.data.model.MusicResponse
import com.bertholucci.domain.model.MusicDomain

class MusicResponseMapper : BaseMapper<MusicResponse, MusicDomain> {

    override fun mapFromDomain(domain: MusicDomain): MusicResponse {
        throw UnsupportedOperationException("Unsupported Operation")
    }

    override fun mapToDomain(model: MusicResponse): MusicDomain {
        return MusicDomain(
            name = model.name,
            artist = model.artist,
            listeners = model.listeners,
            url = model.url,
            image = model.image.map { ImageResponseMapper().mapToDomain(it) }
        )
    }

    fun mapToDomainList(list: List<MusicResponse>): List<MusicDomain> = list.map {
        MusicResponseMapper().mapToDomain(it)
    }
}