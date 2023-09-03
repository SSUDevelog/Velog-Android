package com.velogm.data.dto.response

import com.velogm.domain.model.Follower
import com.velogm.domain.model.DeleteFollower
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowerDto(
    @SerialName("img")
    val img: String,
    @SerialName("name")
    val name: String
) {
    fun toFollowerEntity() = Follower(
        img, name
    )
}

@Serializable
data class DeleteFollowerDto(
    @SerialName("msg")
    val msg: String,
    @SerialName("success")
    val success: Boolean
) {
    fun toDeleteFollowerEntity() = DeleteFollower(
        msg, success
    )
}
