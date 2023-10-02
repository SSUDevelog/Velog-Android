package com.velogm.presentation.ui.bookmark.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.velogm.domain.model.Bookmark
import com.velogm.presentation.databinding.ItemRvBookmarkBinding

class BookmarkViewHolder(
    private val binding: ItemRvBookmarkBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Bookmark) {
        with(binding) {
            tvScrapNumber.text = data.number
            tvScrapTitle.text = data.name
        }
    }
}