package com.velogm.presentation.ui.home.screenhome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.velogm.core_ui.view.ItemDiffCallback
import com.velogm.presentation.databinding.ItemRvPostBinding
import com.velogm.presentation.model.PostModel
import com.velogm.presentation.ui.home.screenhome.viewholder.PostViewHolder

class PostAdapter(
    private val bookMarkClick: (PostModel) -> Unit = { _ -> }
) : ListAdapter<PostModel, PostViewHolder>(
    PostDiffCalback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            ItemRvPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding,bookMarkClick)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val PostDiffCalback =
            ItemDiffCallback<PostModel>(
                onItemsTheSame = { old, new -> old.title == new.title },
                onContentsTheSame = { old, new -> old == new }
            )
    }
}