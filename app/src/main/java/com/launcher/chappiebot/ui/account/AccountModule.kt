package com.launcher.chappiebot.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.launcher.chappiebot.di.ViewModelFactory
import com.launcher.chappiebot.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object AccountModule {
    @JvmStatic
    @Provides
    @IntoMap
    @ViewModelKey(AccountViewModelImpl::class)
    fun provideAccountViewModelIntoMap(accountViewModelImpl: AccountViewModelImpl): ViewModel =
        accountViewModelImpl

    @JvmStatic
    @Provides
    fun provideAccountViewModel(
        accountFragment: AccountFragment,
        viewModelFactory: ViewModelFactory
    ): AccountViewModel =
        ViewModelProviders.of(accountFragment, viewModelFactory)[AccountViewModelImpl::class.java]

}