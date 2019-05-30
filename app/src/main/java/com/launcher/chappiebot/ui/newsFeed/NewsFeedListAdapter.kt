package com.launcher.chappiebot.ui.newsFeed

import android.util.SparseIntArray
import androidx.recyclerview.widget.GridLayoutManager
import com.launcher.chappiebot.BR
import com.launcher.chappiebot.R
import com.launcher.chappiebot.di.scopes.PerFragment
import com.launcher.chappiebot.ui.base.BaseAdapter
import javax.inject.Inject

@PerFragment
class NewsFeedListAdapter @Inject constructor() : BaseAdapter() {

    val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (dataSet[position]) {
                is NewsFeedListItemViewModel -> 1
                else -> 2
            }
        }
    }

    override val viewBindingVariableMap = SparseIntArray().apply {
        listOf(
                R.layout.newsfeed_item_layout,
                R.layout.newsfeed_list_empty_layout,
                R.layout.newsfeed_list_loading_layout
        ).forEach { res ->
            put(res, BR.viewModel)
        }
    }
}