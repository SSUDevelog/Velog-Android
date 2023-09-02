package com.velogm.presentation.ui.follow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.velogm.core_ui.view.ItemDiffCallback
import com.velogm.domain.model.Follower
import com.velogm.presentation.databinding.ItemRvFollowBinding
import com.velogm.presentation.ui.follow.viewholder.FollowerViewHolder

class FollowerAdapter(
    private val onMoveToFollowerClick: (Follower, Int) -> Unit = { _, _ -> }
) :
    ListAdapter<Follower, FollowerViewHolder>(
        FollowerDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemRvFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding, onMoveToFollowerClick)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val FollowerDiffCallback =
            ItemDiffCallback<Follower>(onItemsTheSame = { old, new -> old.name == new.name },
                onContentsTheSame = { old, new -> old == new })
    }
}