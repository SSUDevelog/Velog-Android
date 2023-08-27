package com.velogm.presentation.ui.home.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.fragment.toast
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentHomeSearchBinding
import com.velogm.presentation.ui.addtag.AddTagViewModel
import com.velogm.presentation.ui.addtag.adapter.AddTagAdapter
import com.velogm.presentation.ui.addtag.adapter.PopularTagAdapter
import com.velogm.presentation.ui.addtag.dialog.DeleteDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentHomeSearchBinding>(R.layout.fragment_home_search) {

    private lateinit var myTagAdapter: AddTagAdapter
    private lateinit var popularTagAdapter: PopularTagAdapter
    private val viewModel by viewModels<AddTagViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectMyTagListData()
        collectPopularTagListData()
        search()
        collectEventData()
        myTagAdapter = AddTagAdapter(deleteTagClick = {
            Timber.tag("deleteTag").d(it.tag)
            val dialog = DeleteDialogFragment(
                deleteTag = {
                    viewModel.deleteTag(it.tag)
                }
            )
            dialog.show(childFragmentManager, "delete")
        })
        binding.rvRecentSearchTagList.adapter = myTagAdapter
        popularTagAdapter = PopularTagAdapter()
        binding.rvHomeSearchPopularList.adapter = popularTagAdapter
        setNavigation()
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
                    myTagAdapter.submitList(it.data)
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
        binding.etvHomeSearch.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                true
            } else {
                false
            }
        }
    }

    private fun collectEventData() {
        viewModel.eventData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    viewModel.getTag()
                    Timber.d("getTag")
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

}