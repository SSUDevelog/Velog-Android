package com.velogm.presentation.ui.home.screenhome.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.velogm.presentation.databinding.ItemRvPostBinding
import com.velogm.presentation.model.PostModel
import com.velogm.presentation.model.TagModel
import com.velogm.presentation.ui.addtag.adapter.AddTagAdapter
import timber.log.Timber

class PostViewHolder(
    private val binding: ItemRvPostBinding,
    private val postClick: (PostModel) -> Unit = { _ -> }
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: PostModel) {
        binding.post = data
        binding.root.setOnClickListener {
            postClick(data)
        }
        Timber.tag("test").d(data.tag.map { TagModel(it) }.toString())
        val addTagAdapter = AddTagAdapter()
        binding.rvPostTagList.adapter = addTagAdapter
        addTagAdapter.submitList(data.tag.map { TagModel(it) })
        binding.executePendingBindings()
    }
}