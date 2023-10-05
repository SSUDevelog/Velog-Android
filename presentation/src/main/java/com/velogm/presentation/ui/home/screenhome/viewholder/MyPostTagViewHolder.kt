package com.velogm.presentation.ui.home.screenhome.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.velogm.presentation.databinding.ItemPostTagBinding
import com.velogm.presentation.model.TagModel

class MyPostTagViewHolder(
    private val binding: ItemPostTagBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: TagModel) {
        binding.tag = data
        binding.executePendingBindings()
    }
}