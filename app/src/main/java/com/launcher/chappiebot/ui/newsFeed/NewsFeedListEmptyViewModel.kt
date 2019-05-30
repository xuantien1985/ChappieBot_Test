package com.launcher.chappiebot.ui.newsFeed

import com.launcher.chappiebot.R
import com.launcher.chappiebot.ui.base.ListItem
import javax.inject.Inject

class NewsFeedListEmptyViewModel @Inject constructor(val parent: NewsFeedListViewModel) : ListItem {
    override val id = "newsFeed_list_empty"
    override val type = R.layout.newsfeed_list_empty_layout
}