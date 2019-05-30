package com.launcher.chappiebot.di

import com.launcher.chappiebot.di.scopes.PerFragment
import com.launcher.chappiebot.ui.account.AccountFragment
import com.launcher.chappiebot.ui.account.AccountModule
import com.launcher.chappiebot.ui.newsFeeddetail.NewsFeedDetailFragment
import com.launcher.chappiebot.ui.newsFeeddetail.NewsFeedDetailModule
import com.launcher.chappiebot.ui.newsFeed.NewsFeedListFragment
import com.launcher.chappiebot.ui.newsFeed.NewFeedListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @PerFragment
    @ContributesAndroidInjector(modules = [NewFeedListModule::class])
    abstract fun buildNewsFeedFragment(): NewsFeedListFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [NewsFeedDetailModule::class])
    abstract fun buildNewsFeedDetailFragment(): NewsFeedDetailFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [AccountModule::class])
    abstract fun buildAccountFragment(): AccountFragment
}