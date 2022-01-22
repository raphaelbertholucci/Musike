package com.bertholucci.data.mapper

import com.bertholucci.common.base.BaseMapper
import com.bertholucci.data.model.ImageResponse
import com.bertholucci.domain.model.ImageDomain

class ImageResponseMapper : BaseMapper<ImageResponse, ImageDomain> {

    override fun mapFromDomain(domain: ImageDomain): ImageResponse {
        throw UnsupportedOperationException("Unsupported Operation")
    }

    override fun mapToDomain(model: ImageResponse): ImageDomain {
        return ImageDomain(
            image = model.image,
            size = model.size
        )
    }
}