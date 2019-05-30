package com.launcher.chappiebot.navigation

import androidx.navigation.NavDirections
import com.launcher.chappiebot.ui.newsFeed.NewsFeedListFragmentDirections
import com.launcher.chappiebot.ui.newsFeeddetail.NewsFeedDetailParameter


sealed class NavigationRequest(val destination: NavDirections) {
    data class ListToDetail(val param: NewsFeedDetailParameter)
        : NavigationRequest(NewsFeedListFragmentDirections.actionNewsFeedFragmentToNewsFeedDetailFragment(param))
}