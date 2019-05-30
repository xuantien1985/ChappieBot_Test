package com.launcher.chappiebot.ui.newsFeed

import androidx.lifecycle.MutableLiveData
import com.launcher.chappiebot.model.mapper.NewsFeedModelMapper
import com.launcher.chappiebot.ui.base.BaseViewModel
import com.launcher.chappiebot.ui.base.ListItem
import com.launcher.domain.domain.interactor.GetNewsFeedUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class NewsFeedListViewModelImpl @Inject constructor(
    val getNewsFeedUseCase: GetNewsFeedUseCase,
    val newsFeedModelMapper: NewsFeedModelMapper)
    : BaseViewModel(), NewsFeedListViewModel {

    override val items = MutableLiveData<List<ListItem>>()

    private fun onFail(error: Throwable) {
        Timber.e(error)
        items.value = listOf(NewsFeedListEmptyViewModel(this))
    }

    private fun addLoadingItem() {
        items.value = listOf(NewsFeedListLoadingViewModel())
    }

    override fun onInitialize() {
        refresh()
    }

    override fun refresh() = launch {
        try {
            addLoadingItem()
            val newsFeedModels = withContext(bgDispatcher) {
                val newsfeed = getNewsFeedUseCase.execute()
                newsFeedModelMapper.transformToViewModel(newsfeed, this@NewsFeedListViewModelImpl)
            }
            items.value = newsFeedModels
        } catch (exception: Exception) {
            onFail(exception)
        }
    }
}