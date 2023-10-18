package com.velogm.presentation.ui.home.screenhome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.fragment.toast
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ItemFragmentHomeBinding
import com.velogm.presentation.ui.home.screenhome.adapter.PostAdapter
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

        val data = arguments?.getString("data")
        collectData(data)
        postAdapter = PostAdapter(bookMarkClick = {
            val intent = Intent(requireContext(), WebViewActivity::class.java).apply {
                putExtra("url", it.url)
                putExtra("followName", it.name)
                putExtra("subscribed", it.subscribed)
            }
            startActivity(intent)
        })
        binding.rvPostList.adapter = postAdapter
        collectPostListData(data)
    }

    private fun collectData(data: String?) {
        when (data) {
            "트렌드" -> viewModel.getTrendPost()
            "팔로우" -> viewModel.getFollowPost()
            else -> viewModel.getTagPost(data.toString())
        }
    }

    private fun collectPostListData(data: String?) {
        viewModel.postListData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    val trendPostModel = it.data.trendPostModel
                    postAdapter.submitList(trendPostModel)
                    binding.rvPostList.visibility =
                        if (trendPostModel.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                    if (data == "팔로우") {
                        binding.emptyFollow.emptyFollow.visibility =
                            if (trendPostModel.isEmpty()) View.VISIBLE else View.INVISIBLE
                    } else {
                        binding.emptyPost.emptyPost.visibility =
                            if (trendPostModel.isEmpty()) View.VISIBLE else View.INVISIBLE
                    }

                    binding.progressbar.visibility=View.GONE
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    companion object {
        @JvmStatic
        fun newInstance(data: String): ScreenHomeSlidePageFragment {
            val fragment = ScreenHomeSlidePageFragment()
            val args = Bundle()
            args.putString("data", data)
            fragment.arguments = args
            return fragment
        }
    }
}

