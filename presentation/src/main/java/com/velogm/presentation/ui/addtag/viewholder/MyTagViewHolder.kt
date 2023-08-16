package com.velogm.presentation.ui.addtag.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.velogm.presentation.databinding.ItemMyTagBinding
import com.velogm.presentation.model.TagModel

class MyTagViewHolder(
    private val binding: ItemMyTagBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: TagModel) {
        binding.tag = data
        binding.executePendingBindings()
    }
}