package com.launcher.chappiebot.ui.newsFeed

import androidx.lifecycle.LiveData
import com.launcher.chappiebot.ui.base.IViewModel
import com.launcher.chappiebot.ui.base.ListItem
import kotlinx.coroutines.Job

interface NewsFeedListViewModel : IViewModel {
    val items: LiveData<List<ListItem>>
    fun refresh(): Job
}
