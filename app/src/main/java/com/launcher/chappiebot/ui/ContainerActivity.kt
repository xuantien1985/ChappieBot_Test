package com.launcher.chappiebot.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.launcher.chappiebot.R
import com.launcher.chappiebot.databinding.ActivityContainerBinding
import com.launcher.chappiebot.di.androidx.HasFragmentXInjector
import com.launcher.chappiebot.orientation.ScreenMode
import com.launcher.chappiebot.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_container.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class ContainerActivity : BaseActivity<ActivityContainerBinding, ContainerViewModel>(),
        HasFragmentXInjector {

    private val isLandscape get() = resources.getBoolean(R.bool.landscape)
    override val layoutRes: Int get() = R.layout.activity_container
    override val fragmentXInjector get() = dispatchingFragmentXInjector

    @Inject
    override lateinit var viewModel: ContainerViewModel
    @Inject
    lateinit var dispatchingFragmentXInjector: DispatchingAndroidInjector<Fragment>

    private fun onScreenOrientationChanged(screenMode: ScreenMode) {
        when (screenMode) {
            ScreenMode.FULLSCREEN -> {
                window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
            else -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel.apply {
            landscape = isLandscape
            launch {
                for (screenMode in observeScreenMode()) {
                    onScreenOrientationChanged(screenMode)
                }
            }
        }
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.navHostFragment).navigateUp()

    override fun onBindingCreated(binding: ActivityContainerBinding) {
        binding.viewModel = viewModel
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.accountFragment -> {
                findNavController(R.id.navHostFragment).navigate(R.id.accountFragment)
            }
            R.id.newsFeedListFragment -> {
                findNavController(R.id.navHostFragment).navigate(R.id.newsFeedListFragment)
            }
        }
        true
    }
}
