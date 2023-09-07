package com.velogm.domain.model

data class Follower(
    val img: String,
    val name: String
)

data class DeleteFollower(
    val msg: String,
    val success: Boolean
)