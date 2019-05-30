package com.launcher.chappiebot.ui

import androidx.lifecycle.ViewModelProviders
import com.launcher.chappiebot.di.scopes.PerActivity
import dagger.Module
import dagger.Provides

@Module
object ContainerModule {
    @JvmStatic
    @Provides
    @PerActivity
    fun provideContainerViewModel(containerActivity: ContainerActivity): ContainerViewModel =
            ViewModelProviders.of(containerActivity)[ContainerViewModel::class.java]
}