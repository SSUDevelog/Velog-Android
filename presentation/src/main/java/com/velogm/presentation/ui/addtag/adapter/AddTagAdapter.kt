package com.velogm.presentation.ui.addtag.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.velogm.core_ui.view.ItemDiffCallback
import com.velogm.presentation.databinding.ItemMyTagBinding
import com.velogm.presentation.model.TagModel
import com.velogm.presentation.ui.addtag.viewholder.MyTagViewHolder
import com.velogm.presentation.ui.home.screenhome.PostAdapter

class AddTagAdapter(
    private val deleteTagClick: (TagModel) -> Unit = { _ -> }
) : ListAdapter<TagModel, MyTagViewHolder>(
    MyTagDiffCalback,
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTagViewHolder {
        val binding =
            ItemMyTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyTagViewHolder(binding,deleteTagClick)
    }

    override fun onBindViewHolder(holder: MyTagViewHolder, position: Int) {
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