package com.launcher.domain.model

data class Detail(
    val document_id: String,
    val title: String,
    val description: String,
    val published_date: String,
    val publisher: IPublisher,
    val origin_url: String,
    val template_type: String
//        val content: String
)