package com.launcher.chappiebot.ui.newsFeeddetail

import android.os.Parcelable
import com.launcher.chappiebot.model.NewsFeedModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsFeedDetailParameter(val newsFeedModel: NewsFeedModel) : Parcelable