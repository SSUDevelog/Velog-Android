package com.velogm.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentHomeBinding
import com.velogm.presentation.model.TagModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var demoCollectionAdapter: HomeCollectionAdapter
    private lateinit var viewPager: ViewPager2
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTag()

        viewModel.tagListData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    initAdapter(it.data)
                    initTabLayoutItem(it.data)
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)

    }

    private fun initTabLayoutItem(tagData: List<TagModel>) {
        val homeTabLayout = binding.tablayoutHome
        homeTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        TabLayoutMediator(homeTabLayout, viewPager) { tab, position ->
            tab.text = tagData[position].tag
        }.attach()
    }

    private fun initAdapter(tagData: List<TagModel>) {
        demoCollectionAdapter = HomeCollectionAdapter(requireActivity(), tagData)
        viewPager = binding.pager
        viewPager.adapter = demoCollectionAdapter
        demoCollectionAdapter.setData(tagData)
        demoCollectionAdapter.notifyDataSetChanged()
    }

}