package com.velogm.domain.model


data class PostList(
    val trendPostDtos: List<Post>
)

data class Post(
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