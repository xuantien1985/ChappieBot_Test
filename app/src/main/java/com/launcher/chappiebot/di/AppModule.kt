package com.launcher.chappiebot.di

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.launcher.chappiebot.MainApplication
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideContext(application: MainApplication): Context = application

    @Provides
    @Singleton
    @JvmStatic
    fun provideMainThreadHandler(): Handler = Handler(Looper.getMainLooper())

    @Provides
    @JvmStatic
    fun provideBgDispatcher(): CoroutineDispatcher = Dispatchers.Default
}