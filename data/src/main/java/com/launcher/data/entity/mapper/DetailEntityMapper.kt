package com.launcher.data.entity.mapper

import com.launcher.data.data.entity.mapper.EntityMapper
import com.launcher.data.entity.DetailEntity
import com.launcher.domain.model.Detail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailEntityMapper @Inject constructor()
    : EntityMapper<DetailEntity, Detail> {
    override fun toEntity(from: Detail): DetailEntity {
        return DetailEntity(
            document_id = from.document_id,
            title = from.title,
            description = from.description,
            published_date = from.published_date,
            publisher = from.publisher,
            origin_url = from.origin_url,
            template_type = from.template_type
//                content = from.content
        )
    }

    override fun toDomain(from: DetailEntity): Detail {
        return Detail(
            document_id = requireNotNull(from.document_id),
            title = requireNotNull(from.title),
            description = requireNotNull(from.description).let { s -> "$s\n\n$s\n\n$s" },
            published_date = requireNotNull(from.published_date),
            publisher = from.publisher,
            origin_url = requireNotNull(from.origin_url),
            template_type = requireNotNull(from.template_type)
        )
    }
}