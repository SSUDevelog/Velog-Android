package com.velogm.domain.model


data class FollowerList(
    val followerList: List<Follower>
)

data class Follower(
    val img: String,
    val name: String
)

data class DeleteFollower(
    val msg: String,
    val success: Boolean
)

data class InputFollower(
    val profilePictureURL: String,
    val profileURL: String,
    val userName: String,
    val validate: Boolean
)

