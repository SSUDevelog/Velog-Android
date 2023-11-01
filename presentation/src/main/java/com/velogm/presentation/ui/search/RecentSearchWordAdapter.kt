package com.velogm.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.velogm.core_ui.view.ItemDiffCallback
import com.velogm.presentation.databinding.ItemRecentSearchBinding
import com.velogm.presentation.model.RecentSearchWordModel
import com.velogm.presentation.ui.search.viewholder.RecentSearchWordViewHolder

class RecentSearchWordAdapter() : ListAdapter<RecentSearchWordModel, RecentSearchWordViewHolder>(
    MySearchDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchWordViewHolder {
        val binding =
            ItemRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentSearchWordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentSearchWordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val MySearchDiffCallback = ItemDiffCallback<RecentSearchWordModel>(
            onItemsTheSame = { old, new -> old.recentSearchWord == new.recentSearchWord },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}