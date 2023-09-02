package com.velogm.presentation.ui.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.velogm.core_ui.base.BindingFragment
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
        observeFollower()
    }

    private fun observeFollower() {
        viewModel.getFollower.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    binding.rvFollow.adapter =
                        FollowerAdapter(onMoveToFollowerClick = { it, position ->
                            viewModel.getFollower()
                        }).apply {
                            submitList(it.data)
                        }
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }
}