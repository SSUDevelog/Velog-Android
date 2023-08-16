package com.velogm.presentation.ui.addtag

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.fragment.toast
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentAddTagBinding
import com.velogm.presentation.ui.addtag.adapter.AddTagAdapter
import com.velogm.presentation.ui.addtag.adapter.PopularTagAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AddTagFragment : BindingFragment<FragmentAddTagBinding>(R.layout.fragment_add_tag) {

    private lateinit var myTagAdapter: AddTagAdapter
    private lateinit var popularTagAdapter: PopularTagAdapter
    private val viewModel by viewModels<AddTagViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavigation()
        collectMyTagListData()
        collectPopularTagListData()
        myTagAdapter = AddTagAdapter()
        binding.rvAddTagList.adapter = myTagAdapter
        popularTagAdapter = PopularTagAdapter()
        binding.rvAddTagPopularList.adapter = popularTagAdapter
    }

    private fun setNavigation() {
        binding.toolbarAddTag.setOnClickListener {
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
}