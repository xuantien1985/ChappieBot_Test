package com.launcher.chappiebot.ui.newsFeed

import com.launcher.chappiebot.R
import com.launcher.chappiebot.model.DetailModel
import com.launcher.chappiebot.model.NewsFeedModel
import com.launcher.chappiebot.navigation.NavigationRequest
import com.launcher.chappiebot.ui.base.ListItem
import com.launcher.chappiebot.ui.newsFeeddetail.NewsFeedDetailParameter
import java.io.File

class NewsFeedListItemViewModel(val newsFeedModel: NewsFeedModel, val parent: NewsFeedListViewModel) : ListItem {
    override val id = newsFeedModel.id
    override val type = R.layout.newsfeed_item_layout

    fun onClick() {
        parent.requestNavigation(NavigationRequest.ListToDetail(NewsFeedDetailParameter(newsFeedModel)))
    }
}