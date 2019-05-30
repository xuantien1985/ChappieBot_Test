package com.launcher.data.repository

import com.launcher.data.data.net.NewsFeedApi
import com.launcher.data.entity.DetailCategoryEntity
import com.launcher.data.entity.mapper.DetailEntityMapper
import com.launcher.domain.model.Detail
import com.launcher.domain.repository.DetailRepository
import dagger.Lazy
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepositoryImpl @Inject constructor(
    private val detailApi: Lazy<NewsFeedApi>,
    private val detailEntityMapper: DetailEntityMapper
)
    : DetailRepository {

    override suspend fun getDetail(): List<Detail> = coroutineScope {
        detailApi.get()
            .getDetail()
            .categories
            .flatMap(DetailCategoryEntity::items)
            .map(detailEntityMapper::toDomain)
    }


}