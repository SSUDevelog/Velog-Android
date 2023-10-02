package com.velogm.presentation.ui.bookmark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.velogm.core_ui.view.ItemDiffCallback
import com.velogm.domain.model.Bookmark
import com.velogm.presentation.databinding.ItemRvBookmarkBinding
import com.velogm.presentation.ui.bookmark.viewholder.BookmarkViewHolder

class BookmarkAdapter() :
    ListAdapter<Bookmark, BookmarkViewHolder>(
        BookmarkDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding =
            ItemRvBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val BookmarkDiffCallback =
            ItemDiffCallback<Bookmark>(onItemsTheSame = { old, new -> old.name == new.name },
                onContentsTheSame = { old, new -> old == new })
    }
}