package com.velogm.presentation.model


data class PostModelList(
    val trendPostModel: List<PostModel>
)

data class PostModel(
    val comment: Int,
    val date: String,
    val img: String,
    val like: Int,
    val name: String,
    val subscribed: Boolean,
    val summary: String,
    val tag: List<String>,
    val title: String,
    val url: String
)