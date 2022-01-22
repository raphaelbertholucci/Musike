package com.bertholucci.search.mapper

import com.bertholucci.common.base.BaseMapper
import com.bertholucci.domain.model.ImageDomain
import com.bertholucci.search.model.Image

class ImageMapper : BaseMapper<Image, ImageDomain> {

    override fun mapFromDomain(domain: ImageDomain): Image {
        return Image(
            image = domain.image,
            size = domain.size
        )
    }

    override fun mapToDomain(model: Image): ImageDomain {
        throw UnsupportedOperationException("Operation is not supported!")
    }
}