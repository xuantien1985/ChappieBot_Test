package com.launcher.data.data.di

import android.content.Context
import com.launcher.data.R
import com.launcher.data.data.net.NewsFeedApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideNewsFeedApiAsync(@NewsFeedApiBaseUrl newsFeedApiBaseUrl: String,
                             moshi: Moshi): NewsFeedApi {
        return Retrofit.Builder()
                .baseUrl(newsFeedApiBaseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(NewsFeedApi::class.java)
    }

    @JvmStatic
    @Provides
    @Singleton
    @NewsFeedApiBaseUrl
    fun provideNewsFeedApiBaseUrl(context: Context): String {
        return context.getString(R.string.api_base_url)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }
}