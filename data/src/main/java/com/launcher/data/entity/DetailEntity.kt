package com.launcher.data.entity

import com.launcher.domain.model.IAvatar
import com.launcher.domain.model.IPublisher

data class DetailEntity(
    val document_id: String,
    val title: String?,
    val description: String?,
    val published_date: String?,
    val origin_url: String?,
    val publisher: IPublisher,
    val template_type: String
//    val sections: List

//        val content: String?
)