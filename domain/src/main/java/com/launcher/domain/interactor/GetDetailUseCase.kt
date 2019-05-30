package com.launcher.domain.interactor

import com.launcher.domain.domain.interactor.base.UseCase
import com.launcher.domain.model.Detail
import com.launcher.domain.repository.DetailRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(private val detailRepository: DetailRepository)
    : UseCase<List<Detail>> {
    override suspend fun execute(): List<Detail> = coroutineScope { detailRepository.getDetail() }
}