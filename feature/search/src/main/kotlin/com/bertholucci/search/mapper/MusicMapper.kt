package com.bertholucci.search.mapper

import com.bertholucci.common.base.BaseMapper
import com.bertholucci.domain.model.MusicDomain
import com.bertholucci.search.model.Music

class MusicMapper : BaseMapper<Music, MusicDomain> {

    override fun mapFromDomain(domain: MusicDomain): Music {
        return Music(
            name = domain.name,
            artist = domain.artist,
            listeners = domain.listeners,
            url = domain.url,
            image = domain.image.map { ImageMapper().mapFromDomain(it) }
        )
    }

    override fun mapToDomain(model: Music): MusicDomain {
        throw UnsupportedOperationException("Operation is not supported!")
    }

    fun mapFromDomainList(list: List<MusicDomain>?) = list?.map {
        mapFromDomain(it)
    } ?: emptyList()
}