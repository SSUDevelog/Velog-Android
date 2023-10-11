package com.velogm.data.dto.response

import com.velogm.domain.model.Withdrawal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WithdrawalDto(
    @SerialName("code") val code: Int,
    @SerialName("msg") val msg: String,
    @SerialName("success") val success: Boolean
) {
    fun toWithdrawalEntity() = Withdrawal(
        code, msg, success
    )
}