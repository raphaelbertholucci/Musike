package com.bertholucci.search.mapper

import com.bertholucci.common.base.BaseMapper
import com.bertholucci.domain.model.ImageDomain
import com.bertholucci.search.model.Image

class ImageMapper : BaseMapper<Image, ImageDomain> {

    override fun mapFromDomain(domain: ImageDomain): Image {
        return Image(image = domain.image)
    }

    override fun mapToDomain(model: Image): ImageDomain {
        return ImageDomain(image = model.image)
    }
}