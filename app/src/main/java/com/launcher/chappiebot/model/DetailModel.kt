package com.launcher.chappiebot.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailModel(
    val id: String,
    val title: String,
    val description: String,
    val published_date: String,
    val publisherId: String,
    val publisherName: String,
    val publisherIcon: String,
    val origin_url: String,
    val template_type: String
) : Parcelable
