package com.launcher.data.data.repository

import com.launcher.data.data.entity.NewsFeedCategoryEntity
import com.launcher.data.data.entity.mapper.NewsFeedEntityMapper
import com.launcher.data.data.net.NewsFeedApi
import com.launcher.domain.domain.model.NewsFeed
import com.launcher.domain.domain.repository.NewsFeedRepository

import dagger.Lazy
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsFeedRepositoryImpl @Inject constructor(
    private val newsFeedApi: Lazy<NewsFeedApi>,
    private val newsFeedEntityMapper: NewsFeedEntityMapper)
    : NewsFeedRepository {

    override suspend fun getNewsFeed(): List<NewsFeed> = coroutineScope {
        newsFeedApi.get()
                .getNewsFeed()
                .categories
                .flatMap(NewsFeedCategoryEntity::items)
                .map(newsFeedEntityMapper::toDomain)
    }
}