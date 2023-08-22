package com.velogm.presentation.ui.home.screenhome.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.velogm.presentation.databinding.ItemRvPostBinding
import com.velogm.presentation.model.PostModel

class PostViewHolder(
    private val binding: ItemRvPostBinding,
    private val postClick: (PostModel) -> Unit = { _ -> }
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: PostModel) {
        binding.post = data
        binding.root.setOnClickListener {
            postClick(data)
        }
        binding.executePendingBindings()
    }
}