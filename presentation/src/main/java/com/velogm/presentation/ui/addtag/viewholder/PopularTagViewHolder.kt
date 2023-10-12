package com.velogm.presentation.ui.addtag.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ItemMyTagBinding
import com.velogm.presentation.databinding.ItemPopularTagBinding
import com.velogm.presentation.model.TagModel

class PopularTagViewHolder(
    private val binding: ItemPopularTagBinding,
    private val tagClick: (TagModel) -> Unit = { _ -> }
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: TagModel, position: Int) {
        binding.tag = data
        val tagNum = (position + 1).toString()
        binding.tagNum = tagNum
        binding.root.setOnClickListener {
            tagClick(data)
        }

        // 임시 구현 고칠게요
        when (tagNum) {
            "1", "2", "3" -> {
                binding.tvPopularTagNum.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.main
                    )
                )
            }

            else -> {}
        }

        binding.executePendingBindings()
    }
}