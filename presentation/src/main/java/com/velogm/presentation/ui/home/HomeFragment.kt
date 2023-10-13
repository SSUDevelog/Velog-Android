package com.velogm.presentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentHomeBinding
import com.velogm.presentation.model.TagModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var homeCollectionAdapter: HomeCollectionAdapter
    private lateinit var viewPager: ViewPager2
    private val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTag()
        binding.ivSearchBtn.setOnClickListener {
            findNavController().navigate(
                R.id.navigation_home_to_home_search, bundleOf(
                )
            )
        }
        collectTagListData()
        moveToAddTag()
        homeCollectionAdapter = HomeCollectionAdapter(childFragmentManager, lifecycle)
        viewPager = binding.pager
        viewPager.adapter = homeCollectionAdapter

    }

    override fun onResume() {
        super.onResume()
        viewModel.getTag()
    }

    private fun moveToAddTag() {
        binding.ivPlusBtn.setOnClickListener {
            findNavController().navigate(
                R.id.navigation_home_to_add_tag, bundleOf(
                )
            )
        }
    }

    private fun collectTagListData() {
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

    //하드코딩 수정할게요
    private fun initTabLayoutItem(tagData: List<TagModel>) {
        val defaultTagData = listOf<TagModel>(TagModel("트렌드"), TagModel("팔로우"))
        val finalTagData = defaultTagData + tagData
        Timber.tag("finalTagData").d("$finalTagData")
        val homeTabLayout = binding.tablayoutHome

        TabLayoutMediator(homeTabLayout, viewPager) { tab, position ->
            val tagModel = finalTagData.getOrElse(position) { TagModel("") }
            tab.text = tagModel.tag
        }.attach()
    }

    private fun initAdapter(tagData: List<TagModel>) {
        val defaultTagData = listOf<TagModel>(TagModel("트렌드"), TagModel("팔로우"))
        val finalTagData = defaultTagData + tagData
        Timber.tag("finalTagData").d("$finalTagData")
        homeCollectionAdapter.setData(finalTagData)
        homeCollectionAdapter.notifyDataSetChanged()
    }

}