package com.velogm.presentation.mapper

import com.velogm.domain.model.Tag
import com.velogm.presentation.model.TagModel

fun List<Tag>.toTagModelEntity(): List<TagModel> = map {
    TagModel(
        tag = it.tag
    )
}
