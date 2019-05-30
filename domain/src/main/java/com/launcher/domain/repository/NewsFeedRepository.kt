package com.launcher.domain.domain.repository

import com.launcher.domain.domain.model.NewsFeed

interface NewsFeedRepository {
    suspend fun getNewsFeed(): List<NewsFeed>
}