package com.launcher.chappiebot.ui.newsFeeddetail

import android.view.Surface

interface NewsFeedDetailPlayer {
    var videoUrl: String
    val isReady: Boolean
    var progress: Int
    val duration: Int
    fun mute()
    fun unmute()
    fun registerProgressListener(onProgressListener: (Int) -> Unit)
    fun registerErrorListener(onErrorListener: (Int, Int) -> Unit)
    fun registerPlaybackReadyListener(onPlaybackReadyListener: () -> Unit)
    fun registerCompletionListener(onCompletedListener: () -> Unit)
    fun rewind()
    fun fastForward()
    fun pause()
    fun start()
    fun restart()
    fun reset()
    fun release()
    fun attachSurface(surface: Surface)
}