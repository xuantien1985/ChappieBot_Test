package com.launcher.chappiebot.ui.newsFeeddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.launcher.chappiebot.BR
import com.launcher.chappiebot.extensions.mutableLiveDataOf
import com.launcher.chappiebot.extensions.timeFormat
import com.launcher.chappiebot.model.DetailModel
import com.launcher.chappiebot.model.NewsFeedModel
import com.launcher.chappiebot.model.mapper.DetailModelMapper
import com.launcher.chappiebot.orientation.ScreenMode
import com.launcher.chappiebot.ui.ContainerViewModel
import com.launcher.chappiebot.ui.base.BaseViewModel
import com.launcher.chappiebot.ui.base.ListItem
import com.launcher.chappiebot.ui.newsFeed.NewsFeedListEmptyViewModel
import com.launcher.chappiebot.ui.newsFeed.NewsFeedListLoadingViewModel
import com.launcher.domain.interactor.GetDetailUseCase
import com.launcher.domain.model.Detail
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class NewsFeedDetailViewModelImpl @Inject constructor(
    val getDetailUseCase: GetDetailUseCase,
    val detailMapper: DetailModelMapper,
    val newsFeedDetailParameter: NewsFeedDetailParameter,
    val containerViewModel: ContainerViewModel,
    val stateContext: NewsFeedDetailViewModel.StateContext,
    override var newsFeedDetailPlayer: NewsFeedDetailPlayer)
    : BaseViewModel(), NewsFeedDetailViewModel {

    private var ignoreProgressUpdatesFromUi = false
    private var currentProgressFromPlayer = 0
    private var delayJob: Deferred<Unit>? = null

    override val newsFeed: NewsFeedModel = newsFeedDetailParameter.newsFeedModel

//    private var mDetail = MutableLiveData<DetailModel>()
//    override val detail: DetailModel = mDetail

    override val state get() = stateContext.state
    override val fullscreen = mutableLiveDataOf(false)
    override val overlayVisible = mutableLiveDataOf(false)
    override val soundOn = mutableLiveDataOf(true)
    override val title = mutableLiveDataOf("")
    override val description = mutableLiveDataOf("")
    override val videoLength = mutableLiveDataOf(0)
    override val currentProgressText = mutableLiveDataOf("")
    override val videoLengthText = mutableLiveDataOf("")
    override var videoProgress: Int = 0
        set(value) {
            if (field != value) {
                notifyPropertyChanged(BR.videoProgress)
            }

            if (value != currentProgressFromPlayer) {
                if (!ignoreProgressUpdatesFromUi) {
                    showOverlay()
                    newsFeedDetailPlayer.progress = value
                } else {
                    ignoreProgressUpdatesFromUi = false
                }
            }

            currentProgressText.value = value.timeFormat()

            field = value
        }


    fun resetState() {
        stateContext.state.value = InitStateImpl()
    }

    fun onOverlayCountDownFinished() {
        hideOverlay()
        containerViewModel.enterFullscreen()
    }

    fun onVideoProgress(progress: Int) {
        currentProgressFromPlayer = progress
        videoProgress = currentProgressFromPlayer
    }

    fun onPlaybackReady() {
        videoLength.value = newsFeedDetailPlayer.duration
        videoLengthText.value = newsFeedDetailPlayer.duration.timeFormat()
        stateContext.onPlaybackReady()
    }

    fun showOverlay() {
        if (containerViewModel.screenMode == ScreenMode.FULLSCREEN
                || containerViewModel.screenMode == ScreenMode.LANDSCAPE) {
            overlayVisible.value = true
            launch {
                delayJob?.cancel()
                delayJob = async {
                    delay(5000)
                }
                delayJob?.invokeOnCompletion { error ->
                    if (error == null) {
                        onOverlayCountDownFinished()
                    }
                }
            }
        }
    }

    fun hideOverlay() {
        overlayVisible.value = false
        delayJob?.cancel()
    }

    override fun onInitialize() {
        resetState()
//        title.value = newsFeed.title
//        newsFeedDetailPlayer.apply {
//            videoUrl = newsFeed.source
//            registerPlaybackReadyListener(::onPlaybackReady)
//            registerCompletionListener(stateContext::onCompleted)
//            registerErrorListener(stateContext::onError)
//            registerProgressListener(::onVideoProgress)
//        }

        launch {
            for (screenMode in containerViewModel.observeScreenMode()) {
                stateContext.onScreenModeChanged(screenMode)
            }
        }

        refresh()
    }

    override fun onCleared() {
        super.onCleared()
        hideOverlay()
        newsFeedDetailPlayer.release()
        containerViewModel.exitFullscreen()
    }

    override fun onStop() {
        stateContext.onStop()
    }

    override fun onStart() {
        stateContext.onStart()
    }

    override fun onOverlayClick() {
        showOverlay()
    }

    override fun onPlayClick() {
        showOverlay()
        stateContext.play()
    }

    override fun onCloseVideoClick() {
        hideOverlay()
        resetState()
        containerViewModel.exitFullscreen()
        newsFeedDetailPlayer.reset()
        fullscreen.value = false
//        title.value = newsFeed.title
    }

    override fun onRewindClick() {
        showOverlay()
        newsFeedDetailPlayer.rewind()
    }

    override fun onFastForwardClick() {
        showOverlay()
        newsFeedDetailPlayer.fastForward()
    }

    override fun onFullscreenClick() {
        fullscreen.value = true
        title.value = " "
    }

    override fun onVolumeClick() {
        showOverlay()
        val newVal = !requireNotNull(soundOn.value)
        soundOn.value = newVal
        if (newVal) {
            newsFeedDetailPlayer.unmute()
        } else {
            newsFeedDetailPlayer.mute()
        }
    }

    override fun onLandscapeTransitionFinished() {
        ignoreProgressUpdatesFromUi = true
        containerViewModel.enterFullscreen()
        showOverlay()
    }

    override fun onLoadingAnimationFinished() {
        stateContext.onLoadingAnimationFinished()
    }

    inner class InitStateImpl : NewsFeedDetailViewModel.InitState {
        override val videoVisible = false

        override fun play(context: NewsFeedDetailViewModel.StateContext) {
            context.state.value = if (newsFeedDetailPlayer.isReady) {
                PlayingStateImpl()
            } else {
                LoadingStateImpl()
            }
        }
    }

    inner class PausedStateImpl : NewsFeedDetailViewModel.PausedState {
        override val subActionsTag: String? = null

        init {
            newsFeedDetailPlayer.pause()
        }

        override fun onStart(context: NewsFeedDetailViewModel.StateContext) {
            context.state.value = PlayingStateImpl(false)
        }

        override fun play(context: NewsFeedDetailViewModel.StateContext) {
            context.state.value = if (newsFeedDetailPlayer.isReady) {
                PlayingStateImpl(false)
            } else {
                LoadingStateImpl()
            }
        }
    }

    inner class PlayingStateImpl(override val initial: Boolean = true) : NewsFeedDetailViewModel.PlayingState {
        override val subActionsTag: String? = null

        init {
            newsFeedDetailPlayer.start()
            if (containerViewModel.screenMode == ScreenMode.LANDSCAPE) {
                enterFullscreen()
            }
        }

        private fun enterFullscreen() {
            fullscreen.value = true
            containerViewModel.enterFullscreen()
            showOverlay()
        }

        override fun onStop(context: NewsFeedDetailViewModel.StateContext) {
            context.state.value = PausedStateImpl()
        }

        override fun play(context: NewsFeedDetailViewModel.StateContext) {
            context.state.value = PausedStateImpl()
        }

        override fun onCompleted(context: NewsFeedDetailViewModel.StateContext) {
            context.state.value = CompletedStateImpl()
        }

        override fun onScreenModeChanged(context: NewsFeedDetailViewModel.StateContext, mode: ScreenMode) {
            if (mode == ScreenMode.LANDSCAPE && fullscreen.value != true) {
                enterFullscreen()
            } else if (mode != ScreenMode.LANDSCAPE
                    && mode != ScreenMode.FULLSCREEN
                    && fullscreen.value == true) {
                fullscreen.value = false
            }
        }
    }

    inner class LoadingStateImpl : NewsFeedDetailViewModel.LoadingState {
        override fun onPlaybackReady(context: NewsFeedDetailViewModel.StateContext) {
            context.state.value = LoadingFinishedStateImpl()
        }

        override fun onError(context: NewsFeedDetailViewModel.StateContext, what: Int, extra: Int) {
            context.state.value = ErrorStateImpl()
        }
    }

    inner class LoadingFinishedStateImpl : NewsFeedDetailViewModel.LoadingFinishedState {
        override fun onLoadingAnimationFinished(context: NewsFeedDetailViewModel.StateContext) {
            context.state.value = PlayingStateImpl()
        }
    }

    inner class CompletedStateImpl : NewsFeedDetailViewModel.CompletedState {
        override val subActionsTag: String? = null

        override fun play(context: NewsFeedDetailViewModel.StateContext) {
            newsFeedDetailPlayer.restart()
            context.state.value = PlayingStateImpl(false)
        }
    }

    inner class ErrorStateImpl : NewsFeedDetailViewModel.ErrorState {
        override fun play(context: NewsFeedDetailViewModel.StateContext) {
            context.state.value = LoadingStateImpl()
            newsFeedDetailPlayer.reset()
        }
    }

    // Get Json
    override val items = MutableLiveData<List<ListItem>>()

    private fun onFail(error: Throwable) {
        Timber.e(error)
//        items.value = listOf(NewsFeedListEmptyViewModel(this))
    }

    private fun addLoadingItem() {
        items.value = listOf(NewsFeedListLoadingViewModel())
    }

    override fun refresh() = launch {
        try {
            val mDetail = withContext(bgDispatcher) {
                getDetailUseCase.execute().get(0)
            }

            title.value = mDetail.title
            description.value = mDetail.description
        } catch (exception: Exception) {
            onFail(exception)
        }
    }
}