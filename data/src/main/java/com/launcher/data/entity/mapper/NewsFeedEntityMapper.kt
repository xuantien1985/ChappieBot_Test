package com.launcher.data.data.entity.mapper

import com.launcher.data.data.di.NewsFeedApiBaseUrl
import com.launcher.data.data.entity.NewsFeedEntity
import com.launcher.domain.domain.model.NewsFeed
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsFeedEntityMapper @Inject constructor(
        @NewsFeedApiBaseUrl private val newsFeedApiBaseUrl: String)
    : EntityMapper<NewsFeedEntity, NewsFeed> {
    override fun toEntity(from: NewsFeed): NewsFeedEntity {
        return NewsFeedEntity(
                document_id = from.document_id,
                title = from.title,
                description = from.description,
                content_type = from.content_type,
                published_date = from.published_date,
                publisher = from.publisher,
                origin_url = from.origin_url,
                avatar = from.avatar,
                images = from.images
//                content = from.content
        )
    }

    override fun toDomain(from: NewsFeedEntity): NewsFeed {
        return NewsFeed(
            document_id = requireNotNull(from.document_id),
            title = requireNotNull(from.title),
            description = requireNotNull(from.description).let { s -> "$s\n\n$s\n\n$s" },
            content_type = requireNotNull(from.content_type),
            published_date = requireNotNull(from.published_date),
            publisher = from.publisher,
            origin_url = requireNotNull(from.origin_url),
            avatar = from.avatar,
            images = from.images
//                    content = requireNotNull(from.content)
        )
    }
}