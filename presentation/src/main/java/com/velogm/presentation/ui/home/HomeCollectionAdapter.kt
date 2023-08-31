package com.velogm.presentation.ui.home

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.velogm.presentation.model.TagModel
import com.velogm.presentation.ui.home.screenhome.ScreenHomeSlidePageFragment


class HomeCollectionAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private var dataSet: List<TagModel> = emptyList()
    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun createFragment(position: Int): Fragment {
        return ScreenHomeSlidePageFragment.newInstance(dataSet[position].tag)
    }

    fun setData(newData: List<TagModel>) {
        dataSet = newData
        notifyDataSetChanged()
    }
}