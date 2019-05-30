package com.launcher.chappiebot.ui.newsFeeddetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.transition.Fade
import androidx.transition.Transition
import com.launcher.chappiebot.di.ViewModelFactory
import com.launcher.chappiebot.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object NewsFeedDetailModule {
    @JvmStatic
    @Provides
    @IntoMap
    @ViewModelKey(NewsFeedDetailViewModelImpl::class)
    fun provideDetailViewModelIntoMap(detailViewModelImpl: NewsFeedDetailViewModelImpl): ViewModel =
        detailViewModelImpl

    @JvmStatic
    @Provides
    fun provideDetailViewModel(
        newsFeedDetailFragment: NewsFeedDetailFragment,
        viewModelFactory: ViewModelFactory): NewsFeedDetailViewModel =
            ViewModelProviders.of(newsFeedDetailFragment, viewModelFactory)[NewsFeedDetailViewModelImpl::class.java]

    @JvmStatic
    @Provides
    fun provideDetailParameter(newsFeedDetailFragment: NewsFeedDetailFragment): NewsFeedDetailParameter =
           NewsFeedDetailFragmentArgs.fromBundle(newsFeedDetailFragment.arguments!!).newsfeedDetailParameter

    @JvmStatic
    @Provides
    fun provideTransition(): Transition = Fade()

    @JvmStatic
    @Provides
    fun provideDetailPlayer(detailPlayerImpl: NewsFeedDetailPlayerImpl): NewsFeedDetailPlayer =
        detailPlayerImpl
}