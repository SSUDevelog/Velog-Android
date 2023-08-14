package com.velogm.data.dto.response

import com.velogm.domain.model.Token
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TokenResponseDto(
    @SerialName("code") val code: Int,
    @SerialName("msg") val msg:String,
    @SerialName("success") val success:Boolean,
    @SerialName("token") val token:String
)

fun TokenResponseDto.toTokenEntity()=Token(
    token
)