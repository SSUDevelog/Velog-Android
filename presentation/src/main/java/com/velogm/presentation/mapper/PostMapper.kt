package com.velogm.presentation.mapper

import com.velogm.domain.model.Post
import com.velogm.domain.model.PostList
import com.velogm.presentation.model.PostModel
import com.velogm.presentation.model.PostModelList

fun PostList.toPostModelList(): PostModelList = PostModelList(
        trendPostDtos.map { it.toPostModel() }
)

fun Post.toPostModel(): PostModel = PostModel(
    comment, date, img, like, name, subscribed, summary, tag, title, url
)
