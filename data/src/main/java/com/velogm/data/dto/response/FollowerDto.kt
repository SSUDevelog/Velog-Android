package com.velogm.data.dto.response

import com.velogm.domain.model.DeleteFollower
import com.velogm.domain.model.Follower
import com.velogm.domain.model.InputFollower
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

@Serializable
data class InputFollowerDto(
    @SerialName("profilePictureURL")
    val profilePictureURL: String,
    @SerialName("profileURL")
    val profileURL: String,
    @SerialName("userName")
    val userName: String,
    @SerialName("validate")
    val validate: Boolean
) {
    fun toInputFollowerEntity() = InputFollower(
        profilePictureURL, profileURL, userName, validate
    )
}
