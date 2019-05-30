package com.launcher.data.data.di

import com.launcher.data.data.repository.NewsFeedRepositoryImpl
import com.launcher.data.repository.DetailRepositoryImpl
import com.launcher.domain.domain.repository.NewsFeedRepository
import com.launcher.domain.repository.DetailRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsFeedRepository(newsFeedRepositoryImpl: NewsFeedRepositoryImpl): NewsFeedRepository

    @Binds
    @Singleton
    abstract fun bindDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}