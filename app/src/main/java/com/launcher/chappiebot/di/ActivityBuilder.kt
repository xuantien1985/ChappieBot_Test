package com.launcher.chappiebot.di

import com.launcher.chappiebot.di.scopes.PerActivity
import com.launcher.chappiebot.ui.ContainerActivity
import com.launcher.chappiebot.ui.ContainerModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @PerActivity
    @ContributesAndroidInjector(modules = [ContainerModule::class, FragmentBuilder::class])
    abstract fun buildContainerActivity(): ContainerActivity
}