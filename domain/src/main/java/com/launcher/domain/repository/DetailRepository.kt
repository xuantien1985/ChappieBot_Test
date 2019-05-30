package com.launcher.domain.repository

import com.launcher.domain.model.Detail

interface DetailRepository {
    suspend fun getDetail(): List<Detail>
}