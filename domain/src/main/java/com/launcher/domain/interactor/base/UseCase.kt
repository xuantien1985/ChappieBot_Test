package com.launcher.domain.domain.interactor.base


interface UseCase<T> {
    suspend fun execute(): T
}