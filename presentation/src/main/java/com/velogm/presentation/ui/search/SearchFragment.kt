package com.velogm.presentation.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.fragment.toast
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentHomeSearchBinding
import com.velogm.presentation.ui.addtag.adapter.PopularTagAdapter
import com.velogm.presentation.ui.home.screenhome.adapter.PostAdapter
import com.velogm.presentation.ui.home.screenhome.adapter.PostTagAdapter
import com.velogm.presentation.ui.webview.WebViewActivity
import com.velogm.presentation.util.Debouncer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentHomeSearchBinding>(R.layout.fragment_home_search) {

    private lateinit var postTagAdapter: PostTagAdapter
    private lateinit var popularTagAdapter: PopularTagAdapter
    private lateinit var postAdapter: PostAdapter
    private val searchDebouncer = Debouncer<String>()
    private val viewModel by viewModels<SearchViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTagAdapter()
        initPopularTagAdapter()
        initPostAdapter()
        collectMyTagListData()
        collectPopularTagListData()
        search()
        collectSearchData()
        setNavigation()
    }

    private fun initPostAdapter() {
        postAdapter = PostAdapter(bookMarkClick = {
            Timber.tag("Post").d(it.url)
            val intent = Intent(requireContext(), WebViewActivity::class.java).apply {
                putExtra("url", it.url)
                putExtra("followName", it.name)
            }
            startActivity(intent)
        })
        binding.rvSearchList.adapter = postAdapter
    }

    private fun initPopularTagAdapter() {
        popularTagAdapter = PopularTagAdapter()
        binding.rvHomeSearchPopularList.adapter = popularTagAdapter
    }

    private fun initTagAdapter() {
        postTagAdapter = PostTagAdapter()
        binding.rvRecentSearchTagList.adapter = postTagAdapter
    }

    private fun setNavigation() {
        binding.toolbarHomeSearch.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private fun collectMyTagListData() {
        viewModel.tagListData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    postTagAdapter.submitList(it.data)
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectPopularTagListData() {
        viewModel.tagPopularListData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> {
                    toast("loading")
                }
                is UiState.Success -> {
                    popularTagAdapter.submitList(it.data)
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun search() {
        binding.etvHomeSearch.doAfterTextChanged {
            val keyword: String? =
                if (binding.etvHomeSearch.text.toString() != "") binding.etvHomeSearch.text.toString() else null
            searchDebouncer.setDelay(keyword ?: "", 300L) {
                viewModel.getTagPost(keyword ?: "")
            }
        }
    }

    private fun collectSearchData() {
        viewModel.postListData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    val trendPostModel = it.data.trendPostModel
                    postAdapter.submitList(trendPostModel)
                    binding.rvSearchList.visibility =
                        if (trendPostModel.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
                    binding.clSearch.visibility =
                        if (trendPostModel.isNullOrEmpty()) View.VISIBLE else View.INVISIBLE
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }
}