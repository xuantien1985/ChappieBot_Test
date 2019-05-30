package com.launcher.data.data.entity

import com.launcher.domain.model.IAvatar
import com.launcher.domain.model.IPublisher
import com.squareup.moshi.Json

data class NewsFeedEntity(
        val document_id: String,
        val title: String?,
        val description: String?,
        val content_type: String?,
        val published_date: String?,
        val publisher: IPublisher,
        val origin_url: String?,
        val avatar: IAvatar?,
        val images: List<IAvatar>?
//        val content: String?
)