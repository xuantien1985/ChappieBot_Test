package com.launcher.chappiebot.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsFeedModel(
        val id: String,
        val title: String,
        val description: String,
        val content_type: String,
        val published_date: String,
        val publisherId: String,
        val publisherName: String,
        val publisherIcon: String,
        val origin_url: String,
        val avatarHref: String,
        val avatarColor: String,
        val avatarWidth: Int,
        val avatarHeight: Int,
        val thumb1: String?,
        val thumb2: String?,
        val thumb3: String?
) : Parcelable