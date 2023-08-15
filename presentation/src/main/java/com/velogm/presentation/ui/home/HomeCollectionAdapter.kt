package com.velogm.presentation.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeCollectionAdapter(fragmentActivity: FragmentActivity, data: List<String>) :
    FragmentStateAdapter(fragmentActivity) {
    var dataSet: List<String> = data

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun createFragment(position: Int): Fragment {
        return ScreenHomeSlidePageFragment.newInstance(dataSet[position])
    }

    fun setData(newData: List<String>) {
        this.dataSet = newData
    }

}
