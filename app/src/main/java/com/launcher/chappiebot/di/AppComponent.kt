package com.launcher.chappiebot.di

import com.launcher.chappiebot.MainApplication
import com.launcher.chappiebot.di.androidx.AndroidXInjectionModule
import com.launcher.data.data.di.NetModule
import com.launcher.data.data.di.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,

    NetModule::class,
    RepositoryModule::class,
    ActivityBuilder::class,
    AndroidInjectionModule::class,
    AndroidXInjectionModule::class
])
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}