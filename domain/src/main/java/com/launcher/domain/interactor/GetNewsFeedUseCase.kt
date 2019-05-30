package com.launcher.domain.domain.interactor

import com.launcher.domain.domain.interactor.base.UseCase
import com.launcher.domain.domain.model.NewsFeed
import com.launcher.domain.domain.repository.NewsFeedRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetNewsFeedUseCase @Inject constructor(private val newsFeedRepository: NewsFeedRepository)
    : UseCase<List<NewsFeed>> {
    override suspend fun execute(): List<NewsFeed> = coroutineScope { newsFeedRepository.getNewsFeed() }
}