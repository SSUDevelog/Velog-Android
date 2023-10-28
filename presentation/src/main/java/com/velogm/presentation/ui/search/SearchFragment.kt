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
import com.velogm.presentation.model.TagModel
import com.velogm.presentation.ui.addtag.adapter.PopularTagAdapter
import com.velogm.presentation.ui.home.screenhome.adapter.PostAdapter
import com.velogm.presentation.ui.webview.WebViewActivity
import com.velogm.presentation.util.Debouncer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentHomeSearchBinding>(R.layout.fragment_home_search) {

    // private lateinit var postTagAdapter: PostTagAdapter
    private lateinit var popularTagAdapter: PopularTagAdapter
    private lateinit var postAdapter: PostAdapter
    private lateinit var recentSearchWordAdapter: RecentSearchWordAdapter
    private val searchDebouncer = Debouncer<String>()
    private val viewModel by viewModels<SearchViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPopularTagAdapter()
        initPostAdapter()
        initRecentSearchWordAdapter()
//        collectPopularTagListData()
        collectRecentSearchWord()
        search()
        collectSearchData()
        setNavigation()
        collectEventData()
        setOnClickEventAddTagAllClear()

        popularTagAdapter.submitList(
            listOf(
                TagModel("알고리즘"),
                TagModel("JavaScript"),
                TagModel("TIL"),
                TagModel("Java"),
                TagModel("React"),
                TagModel("프로그래머스"),
                TagModel("코딩테스트"),
                TagModel("Spring"),
                TagModel("CSS")
            )
        )

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
        popularTagAdapter = PopularTagAdapter(tagClick = {
            viewModel.getTagPost(it.tag.toString() ?: "")
        })
        binding.rvHomeSearchPopularList.adapter = popularTagAdapter
    }

    private fun initRecentSearchWordAdapter() {
        recentSearchWordAdapter = RecentSearchWordAdapter()
        binding.rvRecentSearchTagList.adapter = recentSearchWordAdapter
    }

    private fun setNavigation() {
        binding.toolbarHomeSearch.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun collectPopularTagListData() {
        viewModel.tagPopularListData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> {
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
                keyword?.let { viewModel.addSearchWord(keyword) }
            }
        }
    }

    private fun setOnClickEventAddTagAllClear() {
        binding.tvAddTagAllClear.setOnClickListener {
            viewModel.deleteRecentSearchWord()
        }
    }

    private fun collectSearchData() {
        viewModel.postListData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    val trendPostModel = it.data.trendPostModel
                    postAdapter.submitList(trendPostModel)

                    if (trendPostModel.isNullOrEmpty()) {
                        binding.rvSearchList.visibility = View.INVISIBLE
                        toast("검색결과가 없습니다.")
                    } else {
                        binding.rvSearchList.visibility = View.VISIBLE
                    }
                    binding.clSearch.visibility =
                        if (trendPostModel.isNullOrEmpty()) View.VISIBLE else View.INVISIBLE
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectRecentSearchWord() {
        viewModel.getRecentSearchWord.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> recentSearchWordAdapter.submitList(it.data)
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectEventData() {
        viewModel.eventData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> viewModel.getRecentSearchWord()
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

}