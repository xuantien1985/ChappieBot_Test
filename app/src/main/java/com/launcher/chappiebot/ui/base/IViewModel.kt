package com.launcher.chappiebot.ui.base

import androidx.databinding.Observable
import androidx.lifecycle.LifecycleObserver
import com.launcher.chappiebot.navigation.NavigationRequest
import com.launcher.chappiebot.navigation.NavigationRequestListener
import kotlinx.coroutines.CoroutineScope

interface IViewModel : LifecycleObserver, Observable, CoroutineScope {
    fun registerNavigationRequestListener(listener: NavigationRequestListener)
    fun unregisterNavigationRequestListener(listener: NavigationRequestListener)
    fun requestNavigation(navigationRequest: NavigationRequest)
}