package com.velogm.presentation.ui.home.screenhome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ItemFragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class ScreenHomeSlidePageFragment :
    BindingFragment<ItemFragmentHomeBinding>(R.layout.item_fragment_home_) {

    private lateinit var postAdapter: PostAdapter
    private val viewModel by viewModels<ScreenViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postAdapter = PostAdapter(deleteTagClick = {
            Timber.tag("Post").d(it.url)
        })
        binding.rvPostList.adapter = postAdapter

        val data = arguments?.getString("data")

        if (data == "트렌드") {
            viewModel.getTrendPost()
            Timber.d("11111111")
        }
        if (data == "팔로우") {
            viewModel.getFollowPost()
            Timber.d("22222222")
        }
        if (data != "트렌드" && data != "팔로우") {
            viewModel.getTagPost(data.toString())
            Timber.d("33333333")
        }

        collectPostListData()
    }

    private fun collectPostListData() {
        viewModel.postListData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    postAdapter.submitList(it.data.trendPostModel)
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    companion object {
        fun newInstance(data: String): ScreenHomeSlidePageFragment {
            val fragment = ScreenHomeSlidePageFragment()
            val args = Bundle()
            args.putString("data", data)
            fragment.arguments = args
            return fragment
        }
    }
}

