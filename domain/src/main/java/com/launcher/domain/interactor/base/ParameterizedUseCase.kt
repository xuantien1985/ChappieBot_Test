package com.launcher.domain.domain.interactor.base

interface ParameterizedUseCase<T, TParams> {
    suspend fun execute(params: TParams): T
}