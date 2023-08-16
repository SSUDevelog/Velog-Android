package com.velogm.presentation.ui.addtag.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ItemMyTagBinding
import com.velogm.presentation.databinding.ItemPopularTagBinding
import com.velogm.presentation.model.TagModel

class PopularTagViewHolder(
    private val binding: ItemPopularTagBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: TagModel, position: Int) {
        binding.tag = data
        val tagNum = (position + 1).toString()
        binding.tagNum = tagNum


        // 임시 구현 고칠게요
        when (tagNum) {
            "1" -> {
                binding.tvPopularTagNum.setTextColor(ContextCompat.getColor(binding.root.context, R.color.main))
            }
            "2" -> {
                binding.tvPopularTagNum.setTextColor(ContextCompat.getColor(binding.root.context, R.color.main))
            }
            "3" -> {
                binding.tvPopularTagNum.setTextColor(ContextCompat.getColor(binding.root.context, R.color.main))
            }
            else -> {}
        }

        binding.executePendingBindings()
    }
}