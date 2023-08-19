package com.velogm.data.dto.response


import com.velogm.domain.model.Post
import com.velogm.domain.model.PostList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostListDto(
    @SerialName("trendPostDtos")
    val trendPostDtos: List<TrendPostDto>
) {
    fun toPostListEntity() = PostList(
        trendPostDtos.map { it.toPostEntity() }
    )
}

@Serializable
data class TrendPostDto(
    @SerialName("comment")
    val comment: Int,
    @SerialName("date")
    val date: String,
    @SerialName("img")
    val img: String,
    @SerialName("like")
    val like: Int,
    @SerialName("name")
    val name: String,
    @SerialName("subscribed")
    val subscribed: Boolean,
    @SerialName("summary")
    val summary: String,
    @SerialName("tag")
    val tag: List<String>,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
) {
    fun toPostEntity() = Post(
        comment, date, img, like, name, subscribed, summary, tag, title, url
    )
}