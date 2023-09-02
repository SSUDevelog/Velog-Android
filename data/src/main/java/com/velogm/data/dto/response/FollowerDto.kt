package com.velogm.data.dto.response

import com.velogm.domain.model.Follower
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowerDto(
    @SerialName("img")
    val img: String,
    @SerialName("name")
    val name: String,
) {
    fun toFollowerEntity() = Follower(
        img, name
    )
}
