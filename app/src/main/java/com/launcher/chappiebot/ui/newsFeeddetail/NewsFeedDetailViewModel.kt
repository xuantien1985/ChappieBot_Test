package com.launcher.chappiebot.ui.newsFeeddetail

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import com.launcher.chappiebot.extensions.mutableLiveDataOf
import com.launcher.chappiebot.model.DetailModel
import com.launcher.chappiebot.model.NewsFeedModel
import com.launcher.chappiebot.orientation.ScreenMode
import com.launcher.chappiebot.ui.base.IViewModel
import com.launcher.chappiebot.ui.base.ListItem
import kotlinx.coroutines.Job
import javax.inject.Inject

interface NewsFeedDetailViewModel : IViewModel {
    val newsFeed: NewsFeedModel
    val items: LiveData<List<ListItem>>
    fun refresh(): Job

    val newsFeedDetailPlayer: NewsFeedDetailPlayer
    val state: LiveData<State>
    val fullscreen: LiveData<Boolean>
    val overlayVisible: LiveData<Boolean>
    val soundOn: LiveData<Boolean>
    val videoLength: LiveData<Int>
    val title: LiveData<String>
    val description: LiveData<String>
    val currentProgressText: LiveData<String>
    val videoLengthText: LiveData<String>
    @get:Bindable
    var videoProgress: Int

    fun onLoadingAnimationFinished()
    fun onLandscapeTransitionFinished()
    fun onOverlayClick()
    fun onPlayClick()
    fun onCloseVideoClick()
    fun onRewindClick()
    fun onFastForwardClick()
    fun onFullscreenClick()
    fun onVolumeClick()

    interface State {
        val videoVisible get() = true
        val subActionsTag: String? get() = FabContainerBehavior.TAG_FAB_EXCLUDED
        fun play(context: StateContext) {}
        fun onPlaybackReady(context: StateContext) {}
        fun onCompleted(context: StateContext) {}
        fun onError(context: StateContext, what: Int, extra: Int) {}
        fun onLoadingAnimationFinished(context: StateContext) {}
        fun onScreenModeChanged(context: StateContext, mode: ScreenMode) {}
        fun onStop(context: StateContext) {}
        fun onStart(context: StateContext) {}
    }

    interface InitState : State
    interface PausedState : State
    interface PlayingState : State {
        val initial: Boolean
    }
    interface LoadingState : State
    interface LoadingFinishedState : State
    interface CompletedState : State
    interface ErrorState : State

    class StateContext @Inject constructor() {
        private val nonNullState get() = requireNotNull(state.value)
        val state = mutableLiveDataOf<State>()

        fun onStop() {
            nonNullState.onStop(this)
        }

        fun onStart() {
            nonNullState.onStart(this)
        }

        fun onCompleted() {
            nonNullState.onCompleted(this)
        }

        fun onPlaybackReady() {
            nonNullState.onPlaybackReady(this)
        }

        fun onError(what: Int, extra: Int) {
            nonNullState.onError(this, what, extra)
        }

        fun play() {
            nonNullState.play(this)
        }

        fun onLoadingAnimationFinished() {
            nonNullState.onLoadingAnimationFinished(this)
        }

        fun onScreenModeChanged(mode: ScreenMode) {
            nonNullState.onScreenModeChanged(this, mode)
        }
    }
}