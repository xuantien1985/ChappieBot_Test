package com.launcher.chappiebot.ui.newsFeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.launcher.chappiebot.di.ViewModelFactory
import com.launcher.chappiebot.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object NewFeedListModule {
    @JvmStatic
    @Provides
    @IntoMap
    @ViewModelKey(NewsFeedListViewModelImpl::class)
    fun provideNewsFeedViewModelIntoMap(newsFeedViewModelImpl: NewsFeedListViewModelImpl): ViewModel =
        newsFeedViewModelImpl

    @JvmStatic
    @Provides
    fun provideNewsFeedViewModel(
        newsFeedListFragment: NewsFeedListFragment,
        viewModelFactory: ViewModelFactory): NewsFeedListViewModel =
            ViewModelProviders.of(newsFeedListFragment, viewModelFactory)[NewsFeedListViewModelImpl::class.java]
}