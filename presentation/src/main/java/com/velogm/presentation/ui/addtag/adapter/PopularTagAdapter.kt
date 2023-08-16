package com.velogm.presentation.ui.addtag.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.velogm.core_ui.view.ItemDiffCallback
import com.velogm.presentation.databinding.ItemPopularTagBinding
import com.velogm.presentation.model.TagModel
import com.velogm.presentation.ui.addtag.viewholder.PopularTagViewHolder

class PopularTagAdapter(
) : ListAdapter<TagModel, PopularTagViewHolder>(
    PopularTagDiffCalback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTagViewHolder {
        val binding =
            ItemPopularTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularTagViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    companion object {
        private val PopularTagDiffCalback =
            ItemDiffCallback<TagModel>(
                onItemsTheSame = { old, new -> old.tag == new.tag },
                onContentsTheSame = { old, new -> old == new }
            )
    }
}