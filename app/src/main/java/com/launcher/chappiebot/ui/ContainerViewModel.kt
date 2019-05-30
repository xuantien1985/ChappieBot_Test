package com.launcher.chappiebot.ui

import com.launcher.chappiebot.orientation.ScreenMode
import com.launcher.chappiebot.ui.base.BaseViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.runBlocking

class ContainerViewModel : BaseViewModel() {
    private val channel: BroadcastChannel<ScreenMode> = ConflatedBroadcastChannel()
    internal var landscape = false
        set(value) {
            field = value
            if (field && screenMode != ScreenMode.FULLSCREEN) {
                screenMode = ScreenMode.LANDSCAPE
            } else if (!field && screenMode != ScreenMode.UNDEFINED) {
                screenMode = ScreenMode.UNDEFINED
            }
        }

    var screenMode: ScreenMode = ScreenMode.UNDEFINED
        private set(value) {
            field = value
            runBlocking { channel.send(value) }
        }

    fun enterFullscreen() {
        screenMode = ScreenMode.FULLSCREEN
    }

    fun exitFullscreen() {
        screenMode = if (landscape) ScreenMode.LANDSCAPE else ScreenMode.UNDEFINED
    }

    fun observeScreenMode() = channel.openSubscription()
}