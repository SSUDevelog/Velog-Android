package com.velogm.presentation.ui.follow.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.velogm.domain.model.Follower
import com.velogm.presentation.databinding.ItemRvFollowBinding

class FollowerViewHolder(
    private val binding: ItemRvFollowBinding,
    private val onMoveToFollowerClick: (Follower, Int) -> Unit = { _, _ -> }
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Follower) {
        with(binding) {
            follower = data
            ivItemRvFollowFollower.load(data.img){
                transformations(CircleCropTransformation())
            }
            executePendingBindings()
        }
    }

}