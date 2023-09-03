package com.velogm.presentation.ui.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.fragment.toast
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentFollowBinding
import com.velogm.presentation.ui.follow.adapter.FollowerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class FollowFragment : BindingFragment<FragmentFollowBinding>(R.layout.fragment_follow) {

    private val viewModel by viewModels<FollowViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("follow")
        initView()
    }

    private fun initView() {
        viewModel.getFollower()
        collectFollower()
        collectDeleteFollower()
    }

    private fun collectFollower() {
        viewModel.getFollower.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> {
                    toast("Loading")
                }

                is UiState.Success -> {
                    binding.rvFollow.adapter = FollowerAdapter(deleteFollowerClick = {
                        val dialog = DeleteFollowerDialogFragment(deleteFollower = {
                            viewModel.deleteFollower(it.name)
                        })
                        dialog.show(childFragmentManager, "delete")
                    }).apply {
                        submitList(it.data)
                        if (it.data.isNullOrEmpty()) binding.layoutFollowEmpty.visibility =
                            View.VISIBLE
                    }
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectDeleteFollower() {
        viewModel.deleteFollower.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    viewModel.getFollower()
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }
}