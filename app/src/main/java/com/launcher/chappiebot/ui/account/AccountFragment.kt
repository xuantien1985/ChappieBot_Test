package com.launcher.chappiebot.ui.account

import android.os.Bundle
import com.launcher.chappiebot.R
import com.launcher.chappiebot.databinding.FragmentAccountBinding
import com.launcher.chappiebot.ui.base.BaseFragment
import javax.inject.Inject

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>() {

    override val layoutRes = R.layout.fragment_account

    @Inject
    override lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onBindingCreated(binding: FragmentAccountBinding) {
        binding.viewModel = viewModel
    }
}
