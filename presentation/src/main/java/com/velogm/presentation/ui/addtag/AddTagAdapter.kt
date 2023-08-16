package com.velogm.presentation.ui.addtag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.velogm.core_ui.view.ItemDiffCallback
import com.velogm.presentation.databinding.ItemMyTagBinding
import com.velogm.presentation.model.TagModel
import com.velogm.presentation.ui.addtag.viewholder.MyTagViewHolder

class AddTagAdapter(
//    private val onMoveToArticleDetailClick: (ArticleModel, Int) -> Unit = { _, _ -> }
) : ListAdapter<TagModel, MyTagViewHolder>(
    MyTagDiffCalback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTagViewHolder {
        val binding =
            ItemMyTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyTagViewHolder(binding)
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