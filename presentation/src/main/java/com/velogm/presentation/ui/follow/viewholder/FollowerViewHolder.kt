package com.velogm.presentation.ui.follow.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.velogm.domain.model.Follower
import com.velogm.presentation.databinding.ItemRvFollowBinding

class FollowerViewHolder(
    private val binding: ItemRvFollowBinding,
    private val onMoveToFollowerClick: (Follower, Int) -> Unit = { _, _ -> }
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Follower) {

    }

}