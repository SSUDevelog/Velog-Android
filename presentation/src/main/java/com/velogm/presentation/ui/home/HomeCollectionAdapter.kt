package com.velogm.presentation.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.velogm.presentation.model.TagModel

class HomeCollectionAdapter(fragmentActivity: FragmentActivity, data: List<TagModel>) :
    FragmentStateAdapter(fragmentActivity) {
    var dataSet: List<TagModel> = data

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun createFragment(position: Int): Fragment {
        return ScreenHomeSlidePageFragment.newInstance(dataSet[position].tag)
    }

    fun setData(newData: List<TagModel>) {
        this.dataSet = newData
    }

}
