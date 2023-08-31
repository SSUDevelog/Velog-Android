package com.velogm.presentation.ui.home.screenhome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ItemFragmentHomeBinding
import com.velogm.presentation.ui.webview.WebViewActivity
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

        postAdapter = PostAdapter(bookMarkClick = {
            Timber.tag("Post").d(it.url)
            val intent = Intent(requireContext(), WebViewActivity::class.java).apply {
                putExtra("url", it.url)
            }
            startActivity(intent)
        })
        binding.rvPostList.adapter = postAdapter

        val data = arguments?.getString("data")

        when (data) {
            "트렌드" -> viewModel.getTrendPost()
            "팔로우" -> viewModel.getFollowPost()
            else -> viewModel.getTagPost(data.toString())
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

