package com.velogm.presentation.ui.home.screenhome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.velogm.core_ui.view.ItemDiffCallback
import com.velogm.presentation.databinding.ItemPostTagBinding
import com.velogm.presentation.model.TagModel
import com.velogm.presentation.ui.home.screenhome.viewholder.MyPostTagViewHolder

class PostTagAdapter(
) : ListAdapter<TagModel, MyPostTagViewHolder>(
    MyTagDiffCalback,
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostTagViewHolder {
        val binding =
            ItemPostTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPostTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPostTagViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val MyTagDiffCalback =
            ItemDiffCallback<TagModel>(
                onItemsTheSame = { old, new -> old.tag == new.tag },
                onContentsTheSame = { old, new -> old == new }
            )
    }
}