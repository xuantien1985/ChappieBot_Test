package com.launcher.chappiebot.ui.newsFeed

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.core.view.doOnLayout
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.launcher.chappiebot.R
import com.launcher.chappiebot.animation.SimpleAnimationListener
import com.launcher.chappiebot.databinding.FragmentNewsfeedBinding
import com.launcher.chappiebot.extensions.findViewWithTransitionName
import com.launcher.chappiebot.extensions.observe
import com.launcher.chappiebot.navigation.NavigationRequest
import com.launcher.chappiebot.ui.base.BaseFragment
import com.launcher.chappiebot.ui.base.ListItem
import kotlinx.android.synthetic.main.fragment_newsfeed.*
import javax.inject.Inject

class NewsFeedListFragment : BaseFragment<FragmentNewsfeedBinding, NewsFeedListViewModel>() {
    private val videoListAnimController: LayoutAnimationController by lazy {
        AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation_from_bottom)
    }

    @Inject
    override lateinit var viewModel: NewsFeedListViewModel
    @Inject
    internal lateinit var newsFeedListAdapter: NewsFeedListAdapter
    override val layoutRes = R.layout.fragment_newsfeed

    private fun runVideoListAnimation(items: List<ListItem>) {
        if (items.size > 1) {
            newsFeed?.apply {
                layoutAnimation = videoListAnimController
                layoutAnimationListener = SimpleAnimationListener(onEnd = {
                    newsFeed?.layoutAnimation = null
                })
                scheduleLayoutAnimation()
            }
        }
    }

    private fun navigateToDetail(navigationRequest: NavigationRequest.ListToDetail) {
        val newsfeedId = navigationRequest.param.newsFeedModel.id
        navigate(
                destination = navigationRequest.destination,
                extras = view?.findViewWithTransitionName(newsfeedId)?.let { view ->
                    FragmentNavigatorExtras(view to newsfeedId)
                } ?: FragmentNavigatorExtras()
        )
    }

    private fun onItemsFetched(items: List<ListItem>) {
        newsFeedListAdapter.updateItems(items)
        runVideoListAnimation(items)
    }

    override fun onNavigationRequest(navigationRequest: NavigationRequest) {
        when (navigationRequest) {
            is NavigationRequest.ListToDetail -> navigateToDetail(navigationRequest)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.items.observe(this, ::onItemsFetched)
    }

    override fun onStop() {
        super.onStop()
        postponeEnterTransition()
    }

    override fun onBindingCreated(binding: FragmentNewsfeedBinding) {
        binding.viewModel = viewModel
        binding.newsFeed.apply {
            adapter = newsFeedListAdapter
            itemAnimator = null
            doOnLayout {
                startPostponedEnterTransition()
            }
        }
    }
}
