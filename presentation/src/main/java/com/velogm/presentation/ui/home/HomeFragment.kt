package com.velogm.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.velogm.core_ui.base.BindingFragment
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var demoCollectionAdapter: HomeCollectionAdapter
    private lateinit var viewPager: ViewPager2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val testData = listOf<String>("1", "2", "3", )
        initAdapter(testData)
        initTabLayoutItem(testData)
    }

    private fun initTabLayoutItem(testData: List<String>) {
        val homeTabLayout = binding.tablayoutHome
        TabLayoutMediator(homeTabLayout, viewPager) { tab, position ->
            tab.text = testData[position]
        }.attach()
    }

    private fun initAdapter(testData: List<String>) {
        demoCollectionAdapter = HomeCollectionAdapter(requireActivity(), testData)
        viewPager = binding.pager
        viewPager.adapter = demoCollectionAdapter
        demoCollectionAdapter.setData(testData)
        demoCollectionAdapter.notifyDataSetChanged()
    }

}