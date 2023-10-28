package com.velogm.presentation.ui.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.velogm.presentation.databinding.ItemRecentSearchBinding
import com.velogm.presentation.model.RecentSearchWordModel

class RecentSearchWordViewHolder(
    private val binding: ItemRecentSearchBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: RecentSearchWordModel) {
        binding.word = data
        binding.executePendingBindings()
    }
}